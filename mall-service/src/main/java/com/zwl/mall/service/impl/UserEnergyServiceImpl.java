package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.EnergyType;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.dao.mapper.UserEnergyMapper;
import com.zwl.mall.dao.model.UserEnergy;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


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

    @Override
    public void add(Long uid, Integer type) {
        // TODO: 2019/7/1 每日签到、每日分享需要防重控制
        alReadySignIn(type, uid);
        UserEnergy userEnergy = new UserEnergy();
        userEnergy.setType(type);
        userEnergy.setUid(uid);
        EnergyType energyType = EnergyType.getEnergyType(type);
        userEnergy.setEnergyValue(energyType.getValue());
        log.debug(energyType.getDesc());
        userEnergy.insert();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void consume(Long uid, int hours) {
        // TODO: 2019/7/3 用户的可用电力不能低于充电电力  如果当前充电量不足1小时则按照一小时扣除，
        int ableEnergy = getAbleEnergyByUid(uid);
        if (ableEnergy == 0 || hours > ableEnergy) {
            throw new BizException(ErrorEnum.LOW_POWER);
        }
        UserEnergy userEnergy = new UserEnergy();
        EnergyType energyType = EnergyType.getEnergyType(EnergyType.CONSUME_1.getIndex());
        userEnergy.setType(energyType.getIndex());
        userEnergy.setUid(uid);
        // TODO: 2019/7/3 更新过期时间
        //需要消耗的时间
        int needHours = 0;
        UserEnergyExpireTime userEnergyExpireTime = iUserEnergyExpireTimeService.selectOneByUid(uid);
        if (null != userEnergyExpireTime) {
            LocalDateTime expireTime = userEnergyExpireTime.getExpireTime();
            long diffMinute = 24 * 60L - (LocalDateUtil.diffMinute(LocalDateUtil.getNowBeginDate(), expireTime));
            //diffMinute>0 才可以充电
            if (diffMinute <= 0) {
                throw new BizException(ErrorEnum.LOW_FULL);
            }
            if (diffMinute % 60 != 0) {
                needHours = (int) diffMinute / 60 + 1;
            } else {
                needHours = (int) diffMinute / 60;
            }
            expireTime = LocalDateUtil.add(expireTime, 0, 0, 0, needHours, 0, 0);
            userEnergyExpireTime.setExpireTime(expireTime);
            userEnergyExpireTime.setVersion(userEnergyExpireTime.getVersion());
            userEnergyExpireTime.updateById();

        } else {
            needHours = hours;
            // TODO: 2019/7/3  创建过期时间记录
            UserEnergyExpireTime newUserEnergy = new UserEnergyExpireTime();
            newUserEnergy.setUid(uid);
            newUserEnergy.setExpireTime(LocalDateUtil.add(LocalDateTime.now(), 0, 0, 0, needHours, 0, 0));
            newUserEnergy.insert();
        }
        userEnergy.setEnergyValue(energyType.getValue() * needHours);
        log.debug(energyType.getDesc());
        userEnergy.insert();
    }

    private int getAbleEnergyByUid(Long uid) {
        return userEnergyMapper.getAbleEnergyByUid(uid);
    }

    private void alReadySignIn(Integer type, Long uid) {
        int count = userEnergyMapper.alReadySignIn(type, uid);
        if (count > 0) {
            switch (type) {
                case 1:
                    throw new BizException(ErrorEnum.ALREADY_TYPE_1);
                case 2:
                    throw new BizException(ErrorEnum.ALREADY_TYPE_2);
            }

        }
    }
}



