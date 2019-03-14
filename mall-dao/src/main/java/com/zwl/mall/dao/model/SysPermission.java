package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 自定义模板
 * 资源表
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission extends Model<SysPermission> {


    @ApiComment(value = "ID", sample = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiComment(value = "资源名称", sample = "1")
    private String name;

    @ApiComment(value = "权限代码字符串", sample = "1")
    private String perCode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
