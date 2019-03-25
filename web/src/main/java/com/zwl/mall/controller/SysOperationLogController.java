package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.ISysOperationLogService;
import com.zwl.mall.dao.model.SysOperationLog;
import com.zwl.mall.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @since 2019-03-25
 */

@RestController
@RequestMapping("/api/sysOperationLog")
public class SysOperationLogController {


    @Autowired
    public ISysOperationLogService iSysOperationLogService;

    /**
     * 分页查询数据
     *
     * @param sysOperationLog 查询条件
     * @return
     */
    @GetMapping("/getPage")
    public Result getPage(SysOperationLog sysOperationLog, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new SysOperationLog().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<SysOperationLog>().allEq(MapUtil.objectToUnderlineMap(sysOperationLog), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param sysOperationLog 传递的实体
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(SysOperationLog sysOperationLog) throws Exception {
        return ResultUtil.ok(new SysOperationLog().selectOne(new QueryWrapper<SysOperationLog>().allEq(MapUtil.objectToUnderlineMap(sysOperationLog), false)));
    }


    /**
     * 保存
     *
     * @param sysOperationLog 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(SysOperationLog sysOperationLog) throws Exception {
        return ResultUtil.ok(sysOperationLog.insert());
    }

    /**
     * 更新
     *
     * @param sysOperationLog 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(SysOperationLog sysOperationLog) throws Exception {
        return ResultUtil.ok(sysOperationLog.updateById());
    }

    /**
     * 保存或更新
     *
     * @param sysOperationLog 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(SysOperationLog sysOperationLog) throws Exception {
        return ResultUtil.ok(sysOperationLog.insertOrUpdate());
    }
}
