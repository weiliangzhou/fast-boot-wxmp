package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.Constants;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.exception.SysException;
import com.zwl.common.utils.BigDecimalUtil;
import com.zwl.mall.api.*;
import com.zwl.mall.api.vo.MyTaskInfo;
import com.zwl.mall.controller.vo.HomepageVo;
import com.zwl.mall.dao.model.EnergyTaskConfig;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
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
    @Autowired
    private IUserCalculationPowerService iUserCalculationPowerService;
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;
    @Autowired
    private IEnergyTaskConfigService iEnergyTaskConfigService;
    @Autowired
    private IPowerOutputRateService iPowerOutputRateService;


    /**
     * @Date: 2019/7/3 9:31
     * @Author: 二师兄超级帅
     * @Description: 当前算力
     */
    @GetMapping("/user/homepage/info")
    @ApiOperation(value = "首页信息")
    public Result<HomepageVo> powerInfo(@ApiIgnore @CurrentUser UserBase userBase) {
        Long uid = userBase.getId();
//        当前BTC
        String btcInfo = iUserAccountService.getBTCInfoByUid(uid, true);
//        当前算力
        Integer currentPower = iUserCalculationPowerService.getAblePowerByUid(uid);
//        当前剩余电力时间(秒数)
        int currentEnergyExpireSecond = iUserEnergyExpireTimeService.getCurrentEnergyExpireSecondByUid(uid);
//        当前剩余电力（小时）
        int currentEnergyHours = iUserEnergyService.getAbleEnergyValueByUid(uid);
//        当前速率
        BigDecimal calculationPowerByPower = iPowerOutputRateService.getCalculationPowerByPower(currentPower);
        String currentSpeedRate = BigDecimalUtil.objectFormatToString(calculationPowerByPower, null);
//        我的任务
        List<MyTaskInfo> myTaskInfo = iUserEnergyService.getMyTaskInfo(uid);
        return ResultUtil.ok(new HomepageVo(btcInfo, currentPower, currentEnergyExpireSecond, currentEnergyHours, currentSpeedRate, myTaskInfo));
    }

    @ApiOperation(value = "获取BTC信息")
    @GetMapping("/user/user_account/info")
    public Result getMyAccountInfo(@ApiIgnore @CurrentUser UserBase userBase) {
        //获取账户信息
        //公式=可提现的btc-已经提现的btc+今天产出(现在-电力时间*产出率)
        Long uid = userBase.getId();
        String btcInfoByUid = iUserAccountService.getBTCInfoByUid(uid, true);
        return ResultUtil.ok(btcInfoByUid);
    }

    @ApiOperation(value = "任务展示区")
    @GetMapping("/user/task/info")
    public Result<List<MyTaskInfo>> getMyTaskInfo(@ApiIgnore @CurrentUser UserBase userBase) {
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
    @ApiOperation(value = "完成任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "taskId", required = true, paramType = "query", dataType = "Long")
    })
    public Result clickComplete(@ApiIgnore @CurrentUser UserBase userBase, @RequestParam("taskId") Long taskId) {
        EnergyTaskConfig energyTaskConfig = iEnergyTaskConfigService.selectOne(taskId);
        if (energyTaskConfig == null) {
            throw new SysException(ErrorEnum.ARGUMENT_ERROR);
        }
        iUserEnergyService.add(userBase.getId(), taskId);
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
    public Result consume(@ApiIgnore @CurrentUser UserBase userBase, @RequestParam("hours") int hours) {
        iUserEnergyService.consume(userBase.getId(), hours);
        return ResultUtil.ok(Constants.HTTP_RES_CODE_200_VALUE);
    }


    /**
     * @Date: 2019/7/3 9:31
     * @Author: 二师兄超级帅
     * @Description: 当前剩余电力时间(秒数)
     */
    @GetMapping("/user/energy/info")
    @ApiOperation(value = "剩余电力时间(秒数)")
    public Result ableEnergy(@ApiIgnore @CurrentUser UserBase userBase) {
        int currentEnergyExpireSecond = iUserEnergyExpireTimeService.getCurrentEnergyExpireSecondByUid(userBase.getId());
        return ResultUtil.ok(currentEnergyExpireSecond);
    }


}
