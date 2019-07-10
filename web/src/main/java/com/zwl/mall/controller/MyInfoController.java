package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.dao.model.UserAccount;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


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
    private IUserEnergyService iUserEnergyService;
    @Autowired
    private IUserCalculationPowerService iUserCalculationPowerService;
    @Autowired
    private IUserAccountService iUserAccountService;

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/energy/getPage")
    @ApiOperation(value = "电力记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getEnergyPage(@CurrentUser UserBase userBase, int pageNum, int pageSize) {
//        获取可用电力总和
        int ableEnergy = iUserEnergyService.getAbleEnergyValueByUid(userBase.getId());
        IPage<UserEnergy> userEnergyIPage = new UserEnergy().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserEnergy>().eq("uid", userBase.getId()));
        HashMap<String, Object> result = new HashMap<>();
        result.put("ableEnergy", ableEnergy);
        result.put("userEnergyIPage", userEnergyIPage);
        return ResultUtil.ok(result);
    }


    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/CalculationPower/getPage")
    @ApiOperation(value = "算力记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getCalculationPowerPage(@CurrentUser UserBase userBase, int pageNum, int pageSize) {
        //        获取可用算力总和
        int ablePower = iUserCalculationPowerService.getAblePowerByUid(userBase.getId());
        IPage<UserCalculationPower> userCalculationPowerIPage = new UserCalculationPower().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserCalculationPower>().eq("uid", userBase.getId()));
        HashMap<String, Object> result = new HashMap<>();
        result.put("ablePower", ablePower);
        result.put("userCalculationPowerIPage", userCalculationPowerIPage);
        return ResultUtil.ok(result);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/account/getExtractPage")
    @ApiOperation(value = "提币记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "1", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", required = true, paramType = "query", dataType = "int")
    })
    public Result getExtractPage(@CurrentUser UserBase userBase, int pageNum, int pageSize) {
        //        获取总和(实时)
        String total = iUserAccountService.getBTCInfoByUid(userBase.getId(), true);
        String enableBalance = iUserAccountService.getBTCInfoByUid(userBase.getId(), false);
        IPage<UserAccount> userAccountIPage = new UserAccount().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<UserAccount>().eq("uid", userBase.getId()).eq("type", -1));
        HashMap<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("enableBalance", enableBalance);
        result.put("userAccountIPage", userAccountIPage);
        return ResultUtil.ok(result);
    }

}
