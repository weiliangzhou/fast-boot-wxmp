
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.UserEnergy;

/**
 * <p>
 * 电力明细表 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-21
 */
public interface IUserEnergyService extends IService<UserEnergy> {
    /**
     * 增加电力
     *
     * @param uid
     * @param type
     */
    void add(Long uid, Integer type);

}
