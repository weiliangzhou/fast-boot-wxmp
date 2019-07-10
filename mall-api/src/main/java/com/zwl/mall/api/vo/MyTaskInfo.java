package com.zwl.mall.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Date: 2019/7/3 11:54
 * @Author: 二师兄超级帅
 * @Description:
 */

@Data
@AllArgsConstructor
public class MyTaskInfo {
    @ApiModelProperty(value = "类型")
    private int type;
    @ApiModelProperty(value = "任务描述")
    private String desc;
    @ApiModelProperty(value = "是否已完成")
    private boolean complete;
    @ApiModelProperty(value = "按钮文案")
    private String btnName;
}
