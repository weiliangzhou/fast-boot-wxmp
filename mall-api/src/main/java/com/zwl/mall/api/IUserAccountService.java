
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.UserAccount;

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
     * @return
     */
    String getBTCInfoByUid(Long uid);

}
