package com.zwl.mall.api;

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
    StatisticsByDay test(Long id);
}
