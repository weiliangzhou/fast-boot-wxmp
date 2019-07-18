package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.Constants;
import com.zwl.common.constants.EnergyType;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.*;
import com.zwl.mall.api.vo.MyTaskInfo;
import com.zwl.mall.dao.mapper.UserEnergyMapper;
import com.zwl.mall.dao.model.EnergyTaskConfig;
import com.zwl.mall.dao.model.UserEnergy;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 电力明细表 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-21
 */
@Service
@Slf4j
public class UserEnergyServiceImpl extends ServiceImpl<UserEnergyMapper, UserEnergy> implements IUserEnergyService {
    @Autowired
    private UserEnergyMapper userEnergyMapper;
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;
    @Autowired
    private IPowerOutputRateService iPowerOutputRateService;
    @Autowired
    private IUserCalculationPowerService iUserCalculationPowerService;
    @Autowired
    private IEnergyTaskConfigService iEnergyTaskConfigService;

    @Override
    public void add(Long uid, Long taskId) {
        //每日签到、每日分享需要防重控制   一次性任务需要防重
        isTodayCompleted(taskId, uid);
        EnergyTaskConfig energyTaskConfig = iEnergyTaskConfigService.selectOne(taskId);
        Integer taskValue = energyTaskConfig.getTaskValue();
        String description = energyTaskConfig.getDescription();
        Integer taskType = energyTaskConfig.getTaskType();
        UserEnergy userEnergy = new UserEnergy();
        userEnergy.setEnergyType(EnergyType.TYPE_1.getType());
        userEnergy.setUid(uid);
        userEnergy.setEnergyValue(taskValue);
        userEnergy.setTaskDesc(description);
        userEnergy.setTaskId(taskId);
        userEnergy.setTaskType(taskType);
        userEnergy.insert();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void consume(Long uid, int hours) {
        // TODO: 2019/7/16 用户10天 20天


        //用户的可用电力不能低于充电电力  如果当前充电量不足1小时则按照一小时扣除，
        int ableEnergy = getAbleEnergyValueByUid(uid);
        if (ableEnergy == 0 || hours > ableEnergy) {
            throw new BizException(ErrorEnum.LOW_POWER);
        }

        // 算力配置表
        // 获取当前算力
        Integer ablePower = iUserCalculationPowerService.getAblePowerByUid(uid);
        BigDecimal calculationPower = iPowerOutputRateService.getCalculationPowerByPower(ablePower);
        //需要消耗的时间
        int finalNeedHours = 0;
        UserEnergyExpireTime currentEnergyExpireSecondEndTimeByUid = iUserEnergyExpireTimeService.getCurrentEnergyExpireSecondEndTimeByUid(uid);
        if (currentEnergyExpireSecondEndTimeByUid != null) {
            int currentEnergyExpireSecond = currentEnergyExpireSecondEndTimeByUid.getExpireSecond();
            LocalDateTime endTime = currentEnergyExpireSecondEndTimeByUid.getEndTime();

            int needSeconds = 24 * 60 * 60 - currentEnergyExpireSecond;
            //needSecond>0 才可以充电
            if (needSeconds <= 0) {
                throw new BizException(ErrorEnum.LOW_FULL);
            }
            if (needSeconds / 60 % 60 != 0) {
                finalNeedHours = needSeconds / 3600 + 1;
            } else {
                finalNeedHours = needSeconds / 3600;
            }
            finalNeedHours = hours > finalNeedHours ? finalNeedHours : hours;

            LocalDateTime finalStartTime = endTime;
            LocalDateTime finalEndTime = LocalDateUtil.add(endTime, 0, 0, 0, finalNeedHours, 0, 0);

            if (currentEnergyExpireSecond == 0) {
                finalStartTime = LocalDateTime.now();
                finalEndTime = LocalDateUtil.add(finalStartTime, 0, 0, 0, finalNeedHours, 0, 0);
            }
            UserEnergyExpireTime newUserEnergy = new UserEnergyExpireTime();
            newUserEnergy.setUid(uid);
            newUserEnergy.setStartTime(finalStartTime);
            newUserEnergy.setEndTime(finalEndTime);
            newUserEnergy.setCalculationPower(calculationPower);
            newUserEnergy.insert();

            UserEnergy userEnergy = new UserEnergy();
            userEnergy.setUid(uid);
            userEnergy.setEnergyType(EnergyType.TYPE_2.getType());
            userEnergy.setEnergyValue(finalNeedHours);
            userEnergy.setTaskDesc(EnergyType.TYPE_2.getDesc());
            userEnergy.insert();

        } else {
            //不存在记录则 新增
            UserEnergyExpireTime newUserEnergy = new UserEnergyExpireTime();
            newUserEnergy.setUid(uid);
            newUserEnergy.setStartTime(LocalDateTime.now());
            newUserEnergy.setEndTime(LocalDateUtil.add(LocalDateTime.now(), 0, 0, 0, hours, 0, 0));
            newUserEnergy.setCalculationPower(calculationPower);
            newUserEnergy.insert();

            UserEnergy userEnergy = new UserEnergy();
            userEnergy.setUid(uid);
            userEnergy.setEnergyType(EnergyType.TYPE_2.getType());
            userEnergy.setEnergyValue(finalNeedHours);
            userEnergy.setTaskDesc(EnergyType.TYPE_2.getDesc());
            userEnergy.insert();
        }
    }

    @Override
    public List<MyTaskInfo> getMyTaskInfo(Long uid) {
        List<MyTaskInfo> myTaskInfoList = new ArrayList<>();
        List<EnergyTaskConfig> energyTaskConfigList = iEnergyTaskConfigService.listAll();

        for (EnergyTaskConfig energyTaskConfig : energyTaskConfigList) {
            Long id = energyTaskConfig.getId();
            String description = energyTaskConfig.getDescription();
            String title = energyTaskConfig.getTitle();
            Integer taskValue = energyTaskConfig.getTaskValue();
            myTaskInfoList.add(new MyTaskInfo(id, taskValue, title, description, false, Constants.BTN_NAME_1));
        }

        List<UserEnergy> todayCompleteList = getTodayCompleteList(uid);
        if (todayCompleteList != null) {
            for (UserEnergy userEnergy : todayCompleteList) {
                Long taskId = userEnergy.getTaskId();
                for (MyTaskInfo myTaskInfo : myTaskInfoList) {
                    Long id = myTaskInfo.getId();
                    if (id.intValue() == taskId.intValue()) {
                        myTaskInfo.setComplete(true);
                        myTaskInfo.setBtnName(Constants.BTN_NAME_2);
                    }
                }
            }
        }
        return myTaskInfoList;
    }

    @Override
    public int getAbleEnergyValueByUid(Long uid) {
        int ableEnergyByUid = userEnergyMapper.getAbleEnergyByUid(uid);
        return ableEnergyByUid;
    }

    private List<UserEnergy> getTodayCompleteList(Long uid) {
        return userEnergyMapper.getTodayCompleteList(uid);
    }


    private void isTodayCompleted(Long taskId, Long uid) {
        //每日签到、每日分享需要防重控制
        int count = userEnergyMapper.isTodayCompleted(taskId, uid);
        if (count > 0) {
            EnergyTaskConfig energyTaskConfig = iEnergyTaskConfigService.selectOne(taskId);
            if (null != energyTaskConfig) {
                String errorMsg = energyTaskConfig.getErrorMsg();
                throw new BizException(ErrorEnum.IS_TODAY_COMPLETED.getCode(), errorMsg);
            }
        }
        //一次性任务需要防重
        int onceCompleted = userEnergyMapper.isOnceCompleted(taskId, uid);
        if (onceCompleted > 0) {
            throw new BizException(ErrorEnum.IS_ONCE_COMPLETED);
        }

    }
}



