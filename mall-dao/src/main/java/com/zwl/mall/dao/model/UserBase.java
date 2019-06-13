package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2019-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_base")
public class UserBase extends Model<UserBase> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String cellphone;

    private String headImgUrl;

    private String nickName;

    private String gzhOpenId;

    private String xcxOpenId;

    private String unionId;

    private Integer registerFrom;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
