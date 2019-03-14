package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 自定义模板
 * 角色表
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends Model<SysRole> {


    @ApiComment(value = "ID", sample = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiComment(value = "角色名称", sample = "1")
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
