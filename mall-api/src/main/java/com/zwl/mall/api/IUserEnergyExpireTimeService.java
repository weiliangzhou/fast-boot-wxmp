
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.UserEnergyExpireTime;

import java.util.List;

/**
 * <p>
 * 电力明细表 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
public interface IUserEnergyExpireTimeService extends IService<UserEnergyExpireTime> {
    /**
     * 根据uid获取电力过期时间
     *
     * @param uid
     * @return
     */
    UserEnergyExpireTime selectOneByUid(Long uid);

    /**
     * 获取剩余电力时间
     *
     * @param uid
     * @return
     */
    Integer getCurrentEnergyExpireSecondByUid(Long uid);

    /**
     * 查询当前用户今天的时间列表
     *
     * @param uid
     * @return
     */
    List<UserEnergyExpireTime> selectTodayListByUid(Long uid);
}
