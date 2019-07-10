package com.zwl.mall.controller.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zwl.mall.dao.model.UserEnergy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019/7/10 14:17
 * @Author: 二师兄超级帅
 * @Description: 电力记录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEnergyVo {
    private int ableEnergy;
    private IPage<UserEnergy> userEnergyIPage;
}
