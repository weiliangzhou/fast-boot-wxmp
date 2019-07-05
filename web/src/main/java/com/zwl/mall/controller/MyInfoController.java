package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.dao.model.UserCalculationPower;
import com.zwl.mall.dao.model.UserEnergy;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 二师兄超级帅
 * @since 2019-06-21
 */

@RestController
@RequestMapping("/api/user")
@Api(tags = "我的")
@Slf4j
public class MyInfoController {
    @Autowired
    public IUserEnergyService iUserEnergyService;

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/energy/getPage")
    @ApiOperation(value = "电力明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getEnergyPage(@CurrentUser UserBase userBase, int pageNum, int pageSize) {
        return ResultUtil.ok(new UserEnergy().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserEnergy>().eq("uid", userBase.getId())));
    }


    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/CalculationPower/getPage")
    @ApiOperation(value = "算力明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getCalculationPowerPage(@CurrentUser UserBase userBase, int pageNum, int pageSize) {
        return ResultUtil.ok(new UserCalculationPower().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserCalculationPower>().eq("uid", userBase.getId())));
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/account/getExtractPage")
    @ApiOperation(value = "提现明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getExtractPage(@CurrentUser UserBase userBase, int pageNum, int pageSize) {
        return ResultUtil.ok(new UserEnergy().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserEnergy>().eq("uid", userBase.getId()).eq("type", -1)));
    }

}
