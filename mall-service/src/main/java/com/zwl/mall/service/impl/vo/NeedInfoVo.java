package com.zwl.mall.service.impl.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NeedInfoVo {
    @ApiModelProperty(value = "需要邀请数量")
    private Integer needCount;
    @ApiModelProperty(value = "已完成邀请数量")
    private Integer completedCount;
    @ApiModelProperty(value = "需要交易手续费")
    private Integer needMoney;
    @ApiModelProperty(value = "已完成交易手续费")
    private Integer completedMoney;
    @ApiModelProperty(value = "需要交易次数")
    private Integer needTimes;
    @ApiModelProperty(value = "已完成交易次数")
    private Integer completedTimes;

}
