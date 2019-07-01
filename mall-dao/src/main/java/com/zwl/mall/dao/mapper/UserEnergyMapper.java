package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserEnergy;

/**
 * <p>
 * 电力明细表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-21
 */
public interface UserEnergyMapper extends BaseMapper<UserEnergy> {
    /**
     * 当天是否签到过
     *
     * @param type
     * @param uid
     * @return
     */
    int alReadySignIn(Integer type, Long uid);
}
