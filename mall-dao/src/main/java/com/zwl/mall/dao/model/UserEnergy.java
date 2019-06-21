package com.zwl.mall.dao.model;

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
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_energy")
public class UserEnergy extends Model<UserEnergy> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long uid;

    private Integer energyValue;

    @ApiModelProperty(value = "类型：1.签到 2.邀请好友增加 3看视频 4下载app并注册")
    private Integer type;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
