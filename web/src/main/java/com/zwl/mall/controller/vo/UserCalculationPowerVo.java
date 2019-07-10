package com.zwl.mall.controller.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zwl.mall.dao.model.UserCalculationPower;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019/7/10 14:13
 * @Author: 二师兄超级帅
 * @Description: 算力记录
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCalculationPowerVo {
    private int ablePower;
    IPage<UserCalculationPower> userCalculationPowerIPage;
}
