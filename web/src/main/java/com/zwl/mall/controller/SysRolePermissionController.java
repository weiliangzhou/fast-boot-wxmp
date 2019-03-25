package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.ISysRolePermissionService;
import com.zwl.mall.dao.model.SysRolePermission;
import com.zwl.mall.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @since 2019-03-14
 */

@RestController
@RequestMapping("/api/sysRolePermission")
public class SysRolePermissionController {


    @Autowired
    public ISysRolePermissionService iSysRolePermissionService;

    /**
     * 分页查询数据
     *
     * @param sysRolePermission 查询条件
     * @return
     */
    @GetMapping("/getPage")
    public Result getPage(SysRolePermission sysRolePermission, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new SysRolePermission().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<SysRolePermission>().allEq(MapUtil.objectToUnderlineMap(sysRolePermission), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param sysRolePermission 传递的实体
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(SysRolePermission sysRolePermission) throws Exception {
        return ResultUtil.ok(new SysRolePermission().selectOne(new QueryWrapper<SysRolePermission>().allEq(MapUtil.objectToUnderlineMap(sysRolePermission), false)));
    }


    /**
     * 保存
     *
     * @param sysRolePermission 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(SysRolePermission sysRolePermission) throws Exception {
        return ResultUtil.ok(sysRolePermission.insert());
    }

    /**
     * 更新
     *
     * @param sysRolePermission 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(SysRolePermission sysRolePermission) throws Exception {
        return ResultUtil.ok(sysRolePermission.updateById());
    }

    /**
     * 保存或更新
     *
     * @param sysRolePermission 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(SysRolePermission sysRolePermission) throws Exception {
        return ResultUtil.ok(sysRolePermission.insertOrUpdate());
    }
}
