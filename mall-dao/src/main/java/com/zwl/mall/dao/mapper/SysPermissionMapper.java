package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据Role查询Permission
     *
     * @param name
     * @return java.util.List<SysPermission>
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/8/31 11:30
     */
    @Select("  SELECT\n" +
            "        p.id as id,\n" +
            "        p.name as name,\n" +
            "        p.per_code as perCode\n" +
            "        FROM\n" +
            "        sys_role r\n" +
            "        LEFT JOIN sys_role_permission rp ON r.id = rp.role_id\n" +
            "        LEFT JOIN sys_permission p ON p.id = rp.permission_id" +
            "        where r.name=#{name} ")
    List<SysPermission> findPermissionByRole(@Param("name") String name);
}
