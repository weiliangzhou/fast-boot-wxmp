package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 自定义模板
 * 用户表
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends Model<SysUser> {

    @JsonIgnore
    @ApiComment(value = "ID", sample = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiComment(value = "帐号", sample = "1")
    @ApiModelProperty(value = "标题")
    private String account;

    @ApiComment(value = "密码", sample = "1")
    @ApiModelProperty(value = "密码")
    private String password;
    @JsonIgnore
    @ApiComment(value = "昵称", sample = "1")
    private String username;
    @JsonIgnore
    @ApiComment(value = "注册时间", sample = "1")
    private LocalDateTime regTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
