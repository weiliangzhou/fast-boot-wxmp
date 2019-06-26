package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2019/6/24 14:50
 * @Author: 二师兄超级帅
 * @Description: 奖励计算:算力*时间
 */
@Slf4j
@RestController
@RequestMapping("/api/reward")
@Api(tags = "当前余额")
public class RewardController {
    @GetMapping("/info")
    @ApiOperation(value = "获取当前BTC", notes = "获取当前BTC")
    public Result getCurrentReward(@CurrentUser UserBase userBase) {
        // TODO: 2019/6/26 根据算力计算当前btc
        log.info(userBase.getNickName());

        return ResultUtil.ok("ok");
    }
}
