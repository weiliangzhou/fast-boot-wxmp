
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.UserEnergyExpireTime;

import java.math.BigDecimal;
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
     * 获取剩余电力时间
     *
     * @param uid
     * @return
     */
    int getCurrentEnergyExpireSecondByUid(Long uid);

    /**
     * 获取剩余电力时间和最后过期时间
     *
     * @param uid
     * @return
     */
    UserEnergyExpireTime getCurrentEnergyExpireSecondEndTimeByUid(Long uid);

    /**
     * 查询当前用户今天的时间列表
     *
     * @param uid
     * @return
     */
    List<UserEnergyExpireTime> selectTodayListByUid(Long uid);

    /**
     * 查询当前用户昨日的时间列表
     *
     * @param uid
     * @return
     */
    List<UserEnergyExpireTime> selectYesterdayListByUid(Long uid);

    /**
     * 获取今天需要结算的用户uid
     *
     * @return
     */
    List<Long> listTodayUid();

    /**
     * 获取当前用户当前时段的速率
     * @param uid
     * @return
     */
    BigDecimal getCurrentSpeedRateByUid(Long uid);
}
