package com.zwl.mall.dao.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 自定义模板
 *
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("power_output_rate")
public class PowerOutputRate extends Model<PowerOutputRate> {

    @JSONField(serialize = false)
    @JsonIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "算力")
    private Integer power;

    @ApiModelProperty(value = "每秒产出速率")
    private BigDecimal outputRate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    @JSONField(serialize = false)
    @JsonIgnore
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
