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

    private BigDecimal money;

    @ApiModelProperty(value = "交易类型：1挖矿产出 -1转出")
    private Integer type;

    private String typeDesc;

    public String getTypeDesc() {
        if (null != type) {
            switch (this.type) {
                case 1:
                    return "挖矿产出";
                case -1:
                    return "转出";
            }
        }

        return typeDesc;
    }

    private String feature;

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
