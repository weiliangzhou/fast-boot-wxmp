package com.zwl.mall.controller;

import com.alibaba.fastjson.JSON;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.api.vo.MyTaskInfo;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/user/user_account/info")
    public Result getMyAccountInfo(@CurrentUser UserBase userBase) {
        log.info(JSON.toJSONString(userBase));
        // TODO: 2019/6/20 获取账户信息
        //公式=可提现的btc-已经提现的btc+今天产出(现在-电力时间*产出率)
        Long uid = userBase.getId();
        String btcInfoByUid = iUserAccountService.getBTCInfoByUid(uid);
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


}
