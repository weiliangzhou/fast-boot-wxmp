
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.EnergyTaskConfig;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-17
 */
public interface IEnergyTaskConfigService extends IService<EnergyTaskConfig> {
    /**
     * 根据id查询任务
     *
     * @param taskId
     * @return
     */
    EnergyTaskConfig selectOne(Long taskId);

    /**
     * 获取所有任务
     *
     * @return
     */
    List<EnergyTaskConfig> listAll();
}
