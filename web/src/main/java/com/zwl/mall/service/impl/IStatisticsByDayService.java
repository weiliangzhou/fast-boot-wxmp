package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.StatisticsByDay;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-01-25
 */
public interface IStatisticsByDayService extends IService<StatisticsByDay> {
    /**
     * 测试
     * @param id
     * @return
     */
    StatisticsByDay test(Long id);
}
