package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserEnergyExpireTime;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 电力明细表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
public interface UserEnergyExpireTimeMapper extends BaseMapper<UserEnergyExpireTime> {
    /**
     * 获取剩余电力时间
     *
     * @param uid
     * @return
     */
    Map getCurrentEnergyExpireSecondEndTimeByUid(Long uid);

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

}
