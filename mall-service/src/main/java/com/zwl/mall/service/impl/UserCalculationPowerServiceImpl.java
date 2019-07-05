package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.dao.mapper.UserCalculationPowerMapper;
import com.zwl.mall.dao.model.UserCalculationPower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    @Override
    public void add(Long uid, Integer type) {
        if (null != uid) {
            switch (type) {
                case 1:
                    UserCalculationPower userCalculationPower = new UserCalculationPower();
                    userCalculationPower.setUid(uid);
                    userCalculationPower.setType(type);
                    userCalculationPower.setPowerValue(100);
                    userCalculationPower.insert();
                    break;
            }
        }


    }

    @Override
    public int getTotalByUid(Long uid) {
        return userCalculationPowerMapper.getTotalByUid(uid);
    }
}



