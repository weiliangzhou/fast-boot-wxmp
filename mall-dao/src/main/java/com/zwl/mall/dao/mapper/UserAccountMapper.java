package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserAccount;

import java.math.BigDecimal;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    /**
     * 获取可用btc
     * @param uid
     * @return
     */
    BigDecimal getBTCInfoByUid(Long uid);
}
