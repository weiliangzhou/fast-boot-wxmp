package com.zwl.mall.dao.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 自定义模板
 * 电力明细表
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_energy")
public class UserEnergy extends Model<UserEnergy> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JSONField(serialize = false)
    private Long uid;

    @ApiModelProperty(value = "类型 1获得 -1消耗")
    private Integer energyType;
    @ApiModelProperty(value = "小时")
    private Integer energyValue;

    @ApiModelProperty(value = "任务id")
    private Long taskId;

    @ApiModelProperty(value = "任务描述")
    private String taskDesc;

    @ApiModelProperty(value = "任务类型：1一次性 2每日 3仅展示")
    private Integer taskType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    @JSONField(serialize = false)
    private LocalDateTime updateTime;
    @JSONField(serialize = false)
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
