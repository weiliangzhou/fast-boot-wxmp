package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.IStatisticsByDayService;
import com.zwl.mall.dao.mapper.StatisticsByDayMapper;
import com.zwl.mall.dao.model.StatisticsByDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-01-25
 */
@Service
public class StatisticsByDayServiceImpl extends ServiceImpl<StatisticsByDayMapper, StatisticsByDay> implements IStatisticsByDayService {
    @Autowired
    private StatisticsByDayMapper statisticsByDayMapper;

    @Override
    public StatisticsByDay test(Long id) {
        return statisticsByDayMapper.selectOne(new QueryWrapper<StatisticsByDay>().eq("id", id));
    }
}

