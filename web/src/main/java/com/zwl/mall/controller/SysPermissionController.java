package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.ISysPermissionService;
import com.zwl.mall.dao.model.SysPermission;
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
@RequestMapping("/api/sysPermission")
public class SysPermissionController {


    @Autowired
    public ISysPermissionService iSysPermissionService;

    /**
     * 分页查询数据
     *
     * @param sysPermission 查询条件
     * @return
     */
    @GetMapping("/getPage")
    public Result getPage(SysPermission sysPermission, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new SysPermission().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<SysPermission>().allEq(MapUtil.objectToUnderlineMap(sysPermission), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param sysPermission 传递的实体
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(SysPermission sysPermission) throws Exception {
        return ResultUtil.ok(new SysPermission().selectOne(new QueryWrapper<SysPermission>().allEq(MapUtil.objectToUnderlineMap(sysPermission), false)));
    }


    /**
     * 保存
     *
     * @param sysPermission 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(SysPermission sysPermission) throws Exception {
        return ResultUtil.ok(sysPermission.insert());
    }

    /**
     * 更新
     *
     * @param sysPermission 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(SysPermission sysPermission) throws Exception {
        return ResultUtil.ok(sysPermission.updateById());
    }

    /**
     * 保存或更新
     *
     * @param sysPermission 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(SysPermission sysPermission) throws Exception {
        return ResultUtil.ok(sysPermission.insertOrUpdate());
    }
}
