package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.terran4j.commons.api2doc.annotations.ApiComment;
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
 * @since 2019-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserBase extends Model<UserBase> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cellphone;

    private String password;

    private String salt;

    @ApiComment(value = "用户名称", sample = "1")
    private String username;

    @ApiComment(value = "用户昵称", sample = "1")
    private String nickname;

    @ApiComment(value = "头像", sample = "1")
    private String avatar;

    @ApiComment(value = "性别0男，1女", sample = "1")
    private Integer gender;

    @ApiComment(value = "推荐人uid", sample = "1")
    private Long referUid;

    @ApiComment(value = "推荐关系锁定到期时间，到期后为临时锁定关系", sample = "1")
    private LocalDateTime referBindTime;

    @ApiComment(value = "创建时间", sample = "1")
    private LocalDateTime createdAt;

    @ApiComment(value = "更新时间", sample = "1")
    private LocalDateTime updatedAt;

    private Long mid;

    @ApiComment(value = "逻辑删除", sample = "1")
    private Boolean deleted;

    @ApiComment(value = "乐观锁字段", sample = "1")
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
