package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.system.annotation.AdminLog;
import com.zwl.mall.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @since 2019-01-27
 */

@RestController
@RequestMapping("/api/userBase")
public class UserBaseController {


    @Autowired
    public IUserBaseService iUserBaseService;

    /**
     * 分页查询数据
     *
     * @param userBase 查询条件
     * @return
     */
    @GetMapping("/getPage")
    @AdminLog(module = "用户分页查询", description = "用户分页查询")
    public Result getPage(UserBase userBase, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new UserBase().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserBase>().allEq(MapUtil.objectToUnderlineMap(userBase), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param userBase 传递的实体
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(UserBase userBase) throws Exception {
        return ResultUtil.ok(new UserBase().selectOne(new QueryWrapper<UserBase>().allEq(MapUtil.objectToUnderlineMap(userBase), false)));
    }


    /**
     * 保存
     *
     * @param userBase 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(UserBase userBase) throws Exception {
        return ResultUtil.ok(userBase.insert());
    }

    /**
     * 更新
     *
     * @param userBase 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(UserBase userBase) throws Exception {
        return ResultUtil.ok(userBase.updateById());
    }

    /**
     * 保存或更新
     *
     * @param userBase 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(UserBase userBase) throws Exception {
        return ResultUtil.ok(userBase.insertOrUpdate());
    }
}
