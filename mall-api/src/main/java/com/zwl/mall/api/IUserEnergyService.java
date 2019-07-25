
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.api.vo.MyTaskInfo;
import com.zwl.mall.dao.model.UserEnergy;

import java.util.List;

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
     * @param taskId
     */
    void add(Long uid, Long taskId);

    /**
     * 消耗电力
     *
     * @param uid
     * @param hours
     * @param outOpenId
     */
    void consume(Long uid, int hours,String outOpenId);

    /**
     * 获取用户任务信息
     *
     * @param uid
     * @return
     */
    List<MyTaskInfo> getMyTaskInfo(Long uid);


    /**
     * 获取当前用户可用电力
     *
     * @param uid
     * @return
     */
    int getAbleEnergyValueByUid(Long uid);
}
