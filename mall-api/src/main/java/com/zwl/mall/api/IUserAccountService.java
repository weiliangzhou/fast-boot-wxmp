
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.UserAccount;

import java.math.BigDecimal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
public interface IUserAccountService extends IService<UserAccount> {
    /**
     * 获取可用btc
     *
     * @param uid
     * @param hasIncludeToday 是否包含今天实时产出的BTC
     * @return
     */
    String getBTCInfoByUid(Long uid, boolean hasIncludeToday);

    /**
     * 减少账户BTC
     *
     * @param uid
     * @param money
     */
    void reduce(Long uid, BigDecimal money);

}
