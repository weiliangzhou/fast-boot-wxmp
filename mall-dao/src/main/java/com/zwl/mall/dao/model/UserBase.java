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
@TableName("user_base")
public class UserBase extends Model<UserBase> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "手机")
    private String cellphone;

    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "公众号openid")
    private String gzhOpenId;

    @ApiModelProperty(value = "小程序openid")
    private String xcxOpenId;

    @ApiModelProperty(value = "appopneid")
    private String appOpenId;

    @ApiModelProperty(value = "unionid")
    private String unionId;

    @ApiModelProperty(value = "注册来源 1:h5 2:android 3:ios 4:小程序")
    private Integer registerFrom;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private Boolean deleted;

    @ApiModelProperty(value = "是否关注公众号")
    private Boolean isSubscribe;

    @ApiModelProperty(value = "0男1女")
    private Integer sex;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
