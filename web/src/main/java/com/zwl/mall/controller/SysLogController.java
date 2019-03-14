package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.ISysLogService;
import com.zwl.mall.dao.model.SysLog;
import com.zwl.mall.utils.MapUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @since 2019-02-25
 */

@RestController
@RequestMapping("/api/sysLog")
public class SysLogController {


    @Autowired
    public ISysLogService iSysLogService;

    /**
     * 分页查询数据
     *
     * @param sysLog 查询条件
     * @return
     */
    @GetMapping("/getPage")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public Result getPage(SysLog sysLog, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new SysLog().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<SysLog>().allEq(MapUtil.objectToUnderlineMap(sysLog), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param sysLog 传递的实体
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(SysLog sysLog) throws Exception {
        return ResultUtil.ok(new SysLog().selectOne(new QueryWrapper<SysLog>().allEq(MapUtil.objectToUnderlineMap(sysLog), false)));
    }


    /**
     * 保存
     *
     * @param sysLog 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(SysLog sysLog) throws Exception {
        return ResultUtil.ok(sysLog.insert());
    }

    /**
     * 更新
     *
     * @param sysLog 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(SysLog sysLog) throws Exception {
        return ResultUtil.ok(sysLog.updateById());
    }

    /**
     * 保存或更新
     *
     * @param sysLog 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(SysLog sysLog) throws Exception {
        return ResultUtil.ok(sysLog.insertOrUpdate());
    }
}
