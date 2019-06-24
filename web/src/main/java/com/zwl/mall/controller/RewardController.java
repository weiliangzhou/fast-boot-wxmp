package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2019/6/24 14:50
 * @Author: 二师兄超级帅
 * @Description: 奖励计算:算力*时间
 */
@Slf4j
@RestController
public class RewardController {
    public Result getCurrentReward() {

        return ResultUtil.ok("ok");
    }
}
