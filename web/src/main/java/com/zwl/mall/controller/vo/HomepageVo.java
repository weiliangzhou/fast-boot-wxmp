package com.zwl.mall.controller.vo;

import com.zwl.mall.api.vo.MyTaskInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Date: 2019/7/11 16:27
 * @Author: 二师兄超级帅
 * @Description: 首页VO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomepageVo {
    private String btcInfo;
    private Integer currentPower;
    private Integer currentEnergyExpireSecond;
    private List<MyTaskInfo> myTaskInfoList;
}
