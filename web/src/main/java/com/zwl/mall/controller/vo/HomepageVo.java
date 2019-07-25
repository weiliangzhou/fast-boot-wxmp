package com.zwl.mall.controller.vo;

import com.zwl.common.utils.BigDecimalUtil;
import com.zwl.mall.api.vo.MyTaskInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal btcInfo;

    private String btcInfoDesc;

    @ApiModelProperty(value = " 当前每秒增加速率")
    private BigDecimal currentSpeedRate;

    private String currentSpeedRateDesc;

    @ApiModelProperty(value = "当前算力")
    private Integer currentPower;
    @ApiModelProperty(value = " 当前剩余电力时间(秒数)")
    private Integer currentEnergyExpireSecond;
    //    @ApiModelProperty(value = " 当前剩余电力(小时)")
//    private Integer currentEnergyHours;
    @ApiModelProperty(value = "我的任务")
    private List<MyTaskInfo> myTaskInfoList;

    public HomepageVo(BigDecimal btcInfo, BigDecimal currentSpeedRate, Integer currentPower, Integer currentEnergyExpireSecond, List<MyTaskInfo> myTaskInfoList) {
        this.btcInfo = btcInfo;
        this.currentSpeedRate = currentSpeedRate;
        this.currentPower = currentPower;
        this.currentEnergyExpireSecond = currentEnergyExpireSecond;
//        this.currentEnergyHours = currentEnergyHours;
        this.myTaskInfoList = myTaskInfoList;
    }

    public String getBtcInfoDesc() {
        return BigDecimalUtil.toString(this.btcInfo);
    }

    public String getCurrentSpeedRateDesc() {
        return BigDecimalUtil.toString(this.currentSpeedRate);
    }
}
