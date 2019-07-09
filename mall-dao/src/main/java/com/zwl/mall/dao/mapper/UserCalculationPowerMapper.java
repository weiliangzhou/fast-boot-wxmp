package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserCalculationPower;

/**
 * <p>
 * 算力明细表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-24
 */
public interface UserCalculationPowerMapper extends BaseMapper<UserCalculationPower> {
    /**
     * 获取当前用户总算力
     *
     * @param uid
     * @return
     */
    int getTotalByUid(Long uid);

    /**
     * 重置算力到500
     *
     * @param uid
     */
    void resetByUid(Long uid);
}
