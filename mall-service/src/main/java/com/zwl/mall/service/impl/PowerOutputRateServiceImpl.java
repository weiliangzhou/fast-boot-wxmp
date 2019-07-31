package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IPowerOutputRateService;
import com.zwl.mall.dao.mapper.PowerOutputRateMapper;
import com.zwl.mall.dao.model.PowerOutputRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-16
 */
@Service
public class PowerOutputRateServiceImpl extends ServiceImpl<PowerOutputRateMapper, PowerOutputRate> implements IPowerOutputRateService {
    @Override
    public BigDecimal getCalculationPowerByPower(Integer ablePower) {
        if(ablePower>1000)
            ablePower=1000;
        //根据当前时间在user_energy_expire_time表中获取
        PowerOutputRate powerOutputRate = new PowerOutputRate().selectOne(new QueryWrapper<PowerOutputRate>().eq("power", ablePower).eq("deleted", 0));
        return powerOutputRate == null ? new BigDecimal("0") : powerOutputRate.getOutputRate();
    }
}



