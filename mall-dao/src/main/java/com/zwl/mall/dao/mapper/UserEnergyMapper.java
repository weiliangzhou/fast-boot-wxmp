package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.UserEnergy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 电力明细表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-21
 */
public interface UserEnergyMapper extends BaseMapper<UserEnergy> {
    /**
     * 当天是否签到过
     *
     * @param type
     * @param uid
     * @return
     */
    int alReadySignIn(@Param("type") Integer type, @Param("uid") Long uid);

    /**
     * 获取可用电力
     *
     * @param uid
     * @return
     */
    int getAbleEnergyByUid(Long uid);

    /**
     * 获取今天已经完成任务
     *
     * @param uid
     * @return
     */
    List<UserEnergy> getTodayCompleteList(Long uid);
}
