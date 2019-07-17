package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IEnergyTaskConfigService;
import com.zwl.mall.dao.mapper.EnergyTaskConfigMapper;
import com.zwl.mall.dao.model.EnergyTaskConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-17
 */
@Service
public class EnergyTaskConfigServiceImpl extends ServiceImpl<EnergyTaskConfigMapper, EnergyTaskConfig> implements IEnergyTaskConfigService {
    @Autowired
    private EnergyTaskConfigMapper energyTaskConfigMapper;

    @Override
    public EnergyTaskConfig selectOne(Integer type) {
        return new EnergyTaskConfig().selectOne(new QueryWrapper<EnergyTaskConfig>().eq("type", type).eq("deleted", 0));
    }

    @Override
    public List<EnergyTaskConfig> listAll() {
        return energyTaskConfigMapper.selectList(new QueryWrapper<EnergyTaskConfig>().eq("deleted", 0));
    }
}



