package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserEnergyExpireTimeMapper;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p>
 * 电力明细表 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
@Service
public class UserEnergyExpireTimeServiceImpl extends ServiceImpl<UserEnergyExpireTimeMapper, UserEnergyExpireTime> implements IUserEnergyExpireTimeService {
    @Autowired
    private UserEnergyExpireTimeMapper userEnergyExpireTimeMapper;


    @Override
    public int getCurrentEnergyExpireSecondByUid(Long uid) {
        UserEnergyExpireTime currentEnergyExpire = getCurrentEnergyExpireSecondEndTimeByUid(uid);
        int currentEnergyExpireSecond = 0;
        if (currentEnergyExpire != null) {
            currentEnergyExpireSecond = currentEnergyExpire.getExpireSecond();
        }
        return currentEnergyExpireSecond;
    }

    @Override
    public UserEnergyExpireTime getCurrentEnergyExpireSecondEndTimeByUid(Long uid) {
        return userEnergyExpireTimeMapper.getCurrentEnergyExpireSecondEndTimeByUid(uid);
    }

    @Override
    public List<UserEnergyExpireTime> selectTodayListByUid(Long uid) {
        return userEnergyExpireTimeMapper.selectTodayListByUid(uid);

    }

    @Override
    public List<UserEnergyExpireTime> selectYesterdayListByUid(Long uid) {
        return userEnergyExpireTimeMapper.selectYesterdayListByUid(uid);
    }

    @Override
    public List<Long> listTodayUid() {
        return userEnergyExpireTimeMapper.listTodayUid();
    }

    @Override
    public BigDecimal getCurrentSpeedRateByUid(Long uid) {
        BigDecimal currentSpeedRateByUid = userEnergyExpireTimeMapper.getCurrentSpeedRateByUid(uid);
        if (currentSpeedRateByUid == null) {
            return new BigDecimal("0.0000000001");
        }
        return currentSpeedRateByUid;

    }
}



