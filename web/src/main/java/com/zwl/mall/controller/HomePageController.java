package com.zwl.mall.controller;

import com.alibaba.fastjson.JSON;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.Constants;
import com.zwl.common.constants.EnergyType;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.exception.SysException;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.api.vo.MyTaskInfo;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date: 2019/7/3 11:41
 * @Author: 二师兄超级帅
 * @Description: 首页
 */
@RestController
@Slf4j
@Api(value = "首页", tags = "首页")
@RequestMapping("/api")
public class HomePageController {
    @Autowired
    private IUserAccountService iUserAccountService;
    @Autowired
    private IUserEnergyService iUserEnergyService;

    @ApiOperation(value = "获取BTC信息")
    @GetMapping("/user/user_account/info")
    public Result getMyAccountInfo(@CurrentUser UserBase userBase) {
        log.info(JSON.toJSONString(userBase));
        // TODO: 2019/6/20 获取账户信息
        //公式=可提现的btc-已经提现的btc+今天产出(现在-电力时间*产出率)
        Long uid = userBase.getId();
        String btcInfoByUid = iUserAccountService.getBTCInfoByUid(uid, true);
        log.info("调用成功");
        return ResultUtil.ok(btcInfoByUid);
    }

    @ApiOperation(value = "任务展示区")
    @GetMapping("/user/task/info")
    public Result getMyTaskInfo(@CurrentUser UserBase userBase) {
        Long uid = userBase.getId();
        List<MyTaskInfo> myTaskInfo = iUserEnergyService.getMyTaskInfo(uid);
        return ResultUtil.ok(myTaskInfo);
    }

    /**
     * @Date: 2019/6/24 14:17
     * @Author: 二师兄超级帅
     * @Description:
     */
    @GetMapping("/user/task/complete")
    @ApiOperation(value = "点击领取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "\"type\":\"1:每天分享app赠送1小时 2:每天登陆赠送2小时\"\n", required = true, paramType = "query", dataType = "int")
    })
    public Result clickComplete(@CurrentUser UserBase userBase, @RequestParam("type") int type) {
        if (EnergyType.TYPE_1.getIndex() != type && EnergyType.TYPE_2.getIndex() != type) {
            throw new SysException(ErrorEnum.ARGUMENT_ERROR);
        }
        iUserEnergyService.add(userBase.getId(), type);
        return ResultUtil.ok(Constants.HTTP_RES_CODE_200_VALUE);
    }

    /**
     * @Date: 2019/7/3 9:31
     * @Author: 二师兄超级帅
     * @Description: 充电
     */
    @GetMapping("/user/energy/consume")
    @ApiOperation(value = "充电")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hours", value = "1", required = true, paramType = "query", dataType = "int")

    })
    public Result consume(@CurrentUser UserBase userBase, @RequestParam("hours") int hours) {
        iUserEnergyService.consume(userBase.getId(), hours);
        return ResultUtil.ok(Constants.HTTP_RES_CODE_200_VALUE);
    }


}
