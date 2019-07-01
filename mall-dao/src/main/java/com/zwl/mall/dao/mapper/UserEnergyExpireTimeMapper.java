package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 电力明细表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
public interface UserEnergyExpireTimeMapper extends BaseMapper<UserEnergyExpireTime> {
    UserEnergyExpireTime getExpireTimeByUid(Long uid);
}
