package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.Constants;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserCalculationPowerMapper;
import com.zwl.mall.dao.model.UserCalculationPower;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 * 算力明细表 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-24
 */
@Service
public class UserCalculationPowerServiceImpl extends ServiceImpl<UserCalculationPowerMapper, UserCalculationPower> implements IUserCalculationPowerService {
    @Autowired
    private UserCalculationPowerMapper userCalculationPowerMapper;
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;

    @Override
    public void add(Long uid, String nickname, Integer type) {
        if (null != uid) {
            switch (type) {
                case 1:
                    UserCalculationPower userCalculationPower = new UserCalculationPower();
                    userCalculationPower.setUid(uid);
                    userCalculationPower.setType(type);
                    userCalculationPower.setPowerValue(100);
                    userCalculationPower.setNickname(nickname);
                    userCalculationPower.insert();
                    break;
            }
        }


    }


    @Override
    public Integer getAblePowerByUid(Long uid) {
        Integer ablePowerByUid = userCalculationPowerMapper.getAblePowerByUid(uid);
        return ablePowerByUid > 1000 ? 1000 : ablePowerByUid;
    }

    @Override
    public boolean resetByUid(Long uid) {
        //当前过期时间距离当前时间24小时 则重置算力到500
        //查询用户最后一条充电时间，然后与now对比
        UserEnergyExpireTime userEnergyExpireTime = iUserEnergyExpireTimeService.getLastEndTimeByUid(uid);

        if (userEnergyExpireTime == null) {


        } else {
            LocalDateTime endTime = userEnergyExpireTime.getEndTime();
            long diffSecond = LocalDateUtil.diffSecond(endTime, LocalDateTime.now());
            if (diffSecond >= Constants.EXRP_DAY) {
                userCalculationPowerMapper.resetByUid(uid);
                return true;
            }
        }
        return false;

    }
}



