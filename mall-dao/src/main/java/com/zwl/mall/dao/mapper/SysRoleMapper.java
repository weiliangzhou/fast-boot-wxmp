package com.zwl.mall.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwl.mall.dao.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据User查询Role
     *
     * @param account
     * @return java.util.List<SysRole>
     * @author 二师兄超级帅
     * @date 2019/3/7 17:00 2018/8/31 11:30
     */
//    @Select(" SELECT\n" +
//            "        r.id as id,\n" +
//            "        r.name as name\n" +
//            "        FROM\n" +
//            "        sys_user u\n" +
//            "        LEFT JOIN sys_user_role ur ON u.id = ur.user_id\n" +
//            "        LEFT JOIN sys_role r ON r.id = ur.role_id" +
//            "        where u.account=#{account}")
    List<SysRole> findRoleByUser(@Param("account") String account);
}
