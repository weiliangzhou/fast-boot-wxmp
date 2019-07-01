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
 * @since 2019-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_account")
public class UserAccount extends Model<UserAccount> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long uid;

    private BigDecimal money;

    @ApiModelProperty(value = "交易类型：1可提现 2转出")
    private Integer type;

    private String feature;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
