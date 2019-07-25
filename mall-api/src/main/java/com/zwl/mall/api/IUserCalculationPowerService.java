
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.UserCalculationPower;

/**
 * <p>
 * 算力明细表 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-24
 */
public interface IUserCalculationPowerService extends IService<UserCalculationPower> {
    /**
     * 增加算力
     *
     * @param uid
     * @param nickname
     * @param type
     */
    void add(Long uid, String nickname, Integer type);

    /**
     * 获取当前可用算力
     *
     * @param uid
     * @return
     */
    Integer getAblePowerByUid(Long uid);

    /**
     * 重置算力到500
     *
     * @param uid
     */
    void resetByUid(Long uid);


}
