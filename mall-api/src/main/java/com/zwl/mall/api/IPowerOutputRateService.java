
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.dao.model.PowerOutputRate;

import java.math.BigDecimal;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-16
 */
public interface IPowerOutputRateService extends IService<PowerOutputRate> {
    /**
     * 获取当前算力每秒产出率
     *
     * @param ablePower
     * @return
     */
    BigDecimal getCalculationPowerByPower(Integer ablePower);
}
