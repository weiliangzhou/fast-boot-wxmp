package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserEnergyExpireTimeMapper;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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
        Map currentEnergyExpireSecondMap = getCurrentEnergyExpireSecondEndTimeByUid(uid);
        int currentEnergyExpireSecond = 0;
        if (currentEnergyExpireSecondMap != null) {
            currentEnergyExpireSecond = Integer.parseInt(currentEnergyExpireSecondMap.get("expireSecond").toString());
        }
        return currentEnergyExpireSecond;
    }

    @Override
    public Map getCurrentEnergyExpireSecondEndTimeByUid(Long uid) {
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
}



