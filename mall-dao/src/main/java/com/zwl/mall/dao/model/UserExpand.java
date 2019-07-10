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
import java.time.LocalDateTime;

/**
 * <p>
 * 自定义模板
 *
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_expand")
public class UserExpand extends Model<UserExpand> {

    @JsonIgnore
    @JSONField(serialize = false)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JsonIgnore
    @JSONField(serialize = false)
    private Long uid;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "国家")
    private String country;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer version;
    @JsonIgnore
    @JSONField(serialize = false)
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
