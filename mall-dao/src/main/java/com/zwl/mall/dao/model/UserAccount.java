package com.zwl.mall.dao.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zwl.common.utils.BigDecimalUtil;
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

    @JSONField(serialize = false)
    @JsonIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @JsonIgnore
    @JSONField(serialize = false)
    private Long uid;
    @JsonIgnore
    @JSONField(serialize = false)
    @ApiModelProperty(value = "对外openid")
    private String outOpenId;
    @ApiModelProperty(value = "外部订单号")
    private String outTradeNo;

    private BigDecimal money;

    @TableField(exist = false)
    private String moneyDesc;

    public String getMoneyDesc() {
        if (this.money != null) {
            return BigDecimalUtil.objectFormatToString(this.money, "##.############");
        }

        return "0.0000000000";
    }

    @ApiModelProperty(value = "交易类型：1挖矿产出 -1转出")
    private Integer type;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
