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
 * @since 2019-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_energy")
public class UserEnergy extends Model<UserEnergy> {

    @JSONField(serialize = false)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JSONField(serialize = false)
    private Long uid;

    private Integer energyValue;
    @ApiModelProperty(value = "类型：0.新用户奖励 1.签到奖励 2.分享奖励")
    private Integer type;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    @JSONField(serialize = false)
    private Boolean deleted;

    private String description;

    @ApiModelProperty(value = "状态 0未领取 1已领取")
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
