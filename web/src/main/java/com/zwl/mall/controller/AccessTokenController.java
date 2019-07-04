package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.Constants;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.UUIDUtil;
import com.zwl.mall.service.impl.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2019/7/4 16:54
 * @Author: 二师兄超级帅
 * @Description:
 */

@RestController
@Slf4j
@RequestMapping("/api/pub")
@Api(tags = "api接口访问授权")
public class AccessTokenController {
    @Autowired
    private RedisUtil redisUtil;
    private static String KJ_PWD = "2bdff757f974464faa073ddb6b97c805";
    private static String KJ_MID = "kj";

    /**
     * 根据mid，pwd获取accessToken
     *
     * @param mid
     * @param pwd
     * @return
     */
    @ApiOperation(value = "授权", notes = "授权mid=kj,pwd=2bdff757f974464faa073ddb6b97c805")
    @GetMapping("/access_token/{mid}/{pwd}")
    public Result getAccessTokenByMidAndPwd(@PathVariable("mid") String mid,
                                            @PathVariable("pwd") String pwd) {
        // TODO: 2019/6/20 判断帐号密码
        if (KJ_MID.equals(mid) && KJ_PWD.equals(pwd)) {
            String uuid32 = UUIDUtil.getUUID32();
            redisUtil.setString(Constants.ACCESS_TOKEN + mid, uuid32, Constants.EXRP_HOUR * 2);
            return ResultUtil.ok(uuid32);
        }
        // TODO: 2019/6/21 密码5分钟内错误5次则锁定登录24小时

        // TODO: 2019/6/24 插入异常操作记录

        throw new BizException(ErrorEnum.MERCHANT_ERROR);

    }
}
