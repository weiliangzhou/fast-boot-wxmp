package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.utils.MapUtil;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.dao.model.UserEnergy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 二师兄超级帅
 * @since 2019-06-21
 */

@RestController
@RequestMapping("/api/userEnergy")
@Api(tags = "电力")
public class UserEnergyController {


    @Autowired
    public IUserEnergyService iUserEnergyService;

    /**
     * 分页查询数据
     *
     * @param userEnergy 查询条件
     * @return
     */
    @PostMapping("/getPage")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEnergy", value = "分页查询", required = true, paramType = "query", dataType = "UserEnergy"),
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getPage(UserEnergy userEnergy, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new UserEnergy().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserEnergy>().allEq(MapUtil.objectToUnderlineMap(userEnergy), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param userEnergy 传递的实体
     * @return
     */
    @PostMapping("/getByParams")
    @ApiOperation(value = "详情查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEnergy", value = "查询数据", required = true, paramType = "query", dataType = "UserEnergy")
    })
    public Result getByParams(UserEnergy userEnergy) throws Exception {
        return ResultUtil.ok(new UserEnergy().selectOne(new QueryWrapper<UserEnergy>().allEq(MapUtil.objectToUnderlineMap(userEnergy), false)));
    }


    /**
     * 保存
     *
     * @param userEnergy 传递的实体
     * @return 0 失败  1 成功
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEnergy", value = "保存", required = true, paramType = "query", dataType = "UserEnergy")
    })
    public Result save(UserEnergy userEnergy) throws Exception {
        return ResultUtil.ok(userEnergy.insert());
    }

    /**
     * 更新
     *
     * @param userEnergy 传递的实体
     * @return 0 失败  1 成功
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEnergy", value = "更新", required = true, paramType = "query", dataType = "UserEnergy")
    })
    public Result update(UserEnergy userEnergy) throws Exception {
        return ResultUtil.ok(userEnergy.updateById());
    }

    /**
     * 保存或更新
     *
     * @param userEnergy 传递的实体
     * @return 0 失败  1 成功
     */
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEnergy", value = "保存或更新", required = true, paramType = "query", dataType = "UserEnergy")
    })
    public Result saveOrUpdate(UserEnergy userEnergy) throws Exception {
        return ResultUtil.ok(userEnergy.insertOrUpdate());
    }
}
