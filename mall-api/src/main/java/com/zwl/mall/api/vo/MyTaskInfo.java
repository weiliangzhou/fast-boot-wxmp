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
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "小时")
    private int hours;
    @ApiModelProperty(value = "任务类型")
    private int type;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "任务描述")
    private String desc;
    @ApiModelProperty(value = "链接")
    private String hrefUrl;
    @ApiModelProperty(value = "是否已完成")
    private boolean complete;
    @ApiModelProperty(value = "按钮文案")
    private String btnName;
    @ApiModelProperty(value = "图标")
    private String icoUrl;

}
