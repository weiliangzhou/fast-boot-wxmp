package com.zwl.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.utils.MapUtil;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.dao.model.UserCalculationPower;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @since 2019-06-24
 */

@RestController
@RequestMapping("/api/user/CalculationPower")
@Api(tags = "算力")
@Slf4j
public class UserCalculationPowerController {


    @Autowired
    public IUserCalculationPowerService iUserCalculationPowerService;

    /**
     * 分页查询数据
     *
     * @param userCalculationPower 查询条件
     * @return
     */
    @PostMapping("/getPage")
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCalculationPower", value = "分页查询", required = true, paramType = "query", dataType = "UserCalculationPower"),
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getPage(UserCalculationPower userCalculationPower, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new UserCalculationPower().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserCalculationPower>().allEq(MapUtil.objectToUnderlineMap(userCalculationPower), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param userCalculationPower 传递的实体
     * @return
     */
    @PostMapping("/getByParams")
    @ApiOperation(value = "详情查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCalculationPower", value = "查询数据", required = true, paramType = "query", dataType = "UserCalculationPower")
    })
    public Result getByParams(UserCalculationPower userCalculationPower) throws Exception {
        return ResultUtil.ok(new UserCalculationPower().selectOne(new QueryWrapper<UserCalculationPower>().allEq(MapUtil.objectToUnderlineMap(userCalculationPower), false)));
    }


    /**
     * @Date: 2019/6/24 14:17
     * @Author: 二师兄超级帅
     * @Description:
     */
    @PostMapping("/add")
    @ApiOperation(value = "赠送算力")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonObject", value = "{\n" +
//                    "\"channel\":\"渠道来源:1:XX应用 2:XX应用\",\n" +
                    "\"type\":\"1:邀请好友\"\n" +
                    "}", required = true, paramType = "body", dataType = "JSONObject")
    })
    public Result add(
            @CurrentUser UserBase userBase, @RequestBody JSONObject jsonObject) {
        // TODO: 2019/6/24 不同的channel,使用不同的策略
//        Integer channel = jsonObject.getInteger("channel");
        Integer type = jsonObject.getInteger("type");
        switch (type) {
            case 1:
                //邀请好友
                log.info("邀请好友");
                iUserCalculationPowerService.add(userBase.getId(), 1);
                break;
        }
        return ResultUtil.ok("ok");
    }


}
