package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.utils.MapUtil;
import com.zwl.mall.api.ISysUserRoleService;
import com.zwl.mall.dao.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @since 2019-03-14
 */

@RestController
@RequestMapping("/api/sysUserRole")
public class SysUserRoleController {


    @Autowired
    public ISysUserRoleService iSysUserRoleService;

    /**
     * 分页查询数据
     *
     * @param sysUserRole 查询条件
     * @return
     */
    @GetMapping("/getPage")
    public Result getPage(SysUserRole sysUserRole, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new SysUserRole().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<SysUserRole>().allEq(MapUtil.objectToUnderlineMap(sysUserRole), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param sysUserRole 传递的实体
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(SysUserRole sysUserRole) throws Exception {
        return ResultUtil.ok(new SysUserRole().selectOne(new QueryWrapper<SysUserRole>().allEq(MapUtil.objectToUnderlineMap(sysUserRole), false)));
    }


    /**
     * 保存
     *
     * @param sysUserRole 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(SysUserRole sysUserRole) throws Exception {
        return ResultUtil.ok(sysUserRole.insert());
    }

    /**
     * 更新
     *
     * @param sysUserRole 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(SysUserRole sysUserRole) throws Exception {
        return ResultUtil.ok(sysUserRole.updateById());
    }

    /**
     * 保存或更新
     *
     * @param sysUserRole 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(SysUserRole sysUserRole) throws Exception {
        return ResultUtil.ok(sysUserRole.insertOrUpdate());
    }
}
