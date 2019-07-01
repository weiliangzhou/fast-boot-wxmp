package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.EnergyType;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.dao.mapper.UserEnergyMapper;
import com.zwl.mall.dao.model.UserEnergy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    @Override
    public void add(Long uid, Integer type) {
        // TODO: 2019/7/1 每日签到、每日分享需要防重控制
        if (EnergyType.TYPE_1.getIndex() != type) {
            alReadySignIn(type, uid);
        }
        UserEnergy userEnergy = new UserEnergy();
        userEnergy.setType(type);
        userEnergy.setUid(uid);
        EnergyType energyType = EnergyType.getEnergyType(type);
        userEnergy.setEnergyValue(energyType.getValue());
        log.info(energyType.getDesc());
        userEnergy.insert();
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



