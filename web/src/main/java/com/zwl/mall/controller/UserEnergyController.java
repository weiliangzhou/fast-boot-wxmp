package com.zwl.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.utils.MapUtil;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.dao.model.UserEnergy;
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
 * @since 2019-06-21
 */

@RestController
@RequestMapping("/api/userEnergy")
@Api(tags = "电力")
@Slf4j
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
     * @Date: 2019/6/24 14:17
     * @Author: 二师兄超级帅
     * @Description:
     */
    @PostMapping("/add")
    @ApiOperation(value = "赠送电力")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonObject", value = "{\n" +
                    "\"channel\":\"渠道来源:1:XX应用\",\n" +
                    "\"type\":\"1:新用户注册赠送120电力 2:每天分享app赠送1小时 3:每天登陆赠送2小时\"\n" +
                    "}", required = true, paramType = "body", dataType = "JSONObject")
    })
    public Result add(@CurrentUser UserBase userBase, @RequestBody JSONObject jsonObject) {
        // TODO: 2019/6/24 不同的channel,使用不同的策略
        Integer type = jsonObject.getInteger("type");
        iUserEnergyService.add(userBase.getId(), type);
        return ResultUtil.ok("ok");
    }
}
