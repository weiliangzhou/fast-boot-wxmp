package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserEnergyExpireTimeMapper;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public UserEnergyExpireTime selectOneByUid(Long uid) {
        return new UserEnergyExpireTime().selectOne(new QueryWrapper<UserEnergyExpireTime>().eq("uid", uid).eq("deleted", 0));
    }

    @Override
    public Integer getCurrentEnergyExpireSecondByUid(Long uid) {
        Integer expireSecondByUid = userEnergyExpireTimeMapper.getCurrentEnergyExpireSecondByUid(uid);
        if (expireSecondByUid > 0) {
            return expireSecondByUid;
        }
        return 0;
    }
}



