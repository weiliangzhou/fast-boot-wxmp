package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 自定义模板
 * 角色资源表
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRolePermission extends Model<SysRolePermission> {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer roleId;

    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
