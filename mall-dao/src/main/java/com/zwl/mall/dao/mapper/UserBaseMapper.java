package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserBase;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-13
 */
public interface UserBaseMapper extends BaseMapper<UserBase> {
    /**
     * 获取用户注册天数
     *
     * @param uid
     * @return
     */
    Integer countRegisterDayByUid(Long uid);

    /**
     * 获取当前推荐人数
     *
     * @param uid
     * @return
     */
    Integer countByReferUid(Long uid);
}
