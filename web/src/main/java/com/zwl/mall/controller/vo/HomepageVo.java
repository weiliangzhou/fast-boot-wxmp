package com.zwl.mall.controller.vo;

import com.zwl.mall.api.vo.MyTaskInfo;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "当前BTC")
    private String btcInfo;
    @ApiModelProperty(value = "当前算力")
    private Integer currentPower;
    @ApiModelProperty(value = " 当前剩余电力时间(秒数)")
    private Integer currentEnergyExpireSecond;
    @ApiModelProperty(value = " 当前剩余电力(小时)")
    private Integer currentEnergyHours;
    @ApiModelProperty(value = "我的任务")
    private List<MyTaskInfo> myTaskInfoList;
}
