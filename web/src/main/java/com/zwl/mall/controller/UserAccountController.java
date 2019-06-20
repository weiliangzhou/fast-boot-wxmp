package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.Constants;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.mall.service.impl.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 二师兄超级帅 on 2019/6/20.
 */
@RestController
@Slf4j
@RequestMapping("/api/out/user_account")
public class UserAccountController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/info/{phone}/{mid}/{access_token}")
    public Result getUserAccountInfo(@PathVariable("phone") String phone,
                                     @PathVariable("mid") String mid,
                                     @PathVariable("access_token") String accessToken
    ) {
        if (StringUtils.isBlank(mid)) {
            throw new BizException(ErrorEnum.ACCESSTOKEN_EXPIRATION);
        }
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(mid)) {
            throw new BizException(ErrorEnum.ACCESSTOKEN_EXPIRATION);
        }

        // TODO: 2019/6/20 校验accessToken
        String redisAccessToken = redisUtil.getString(Constants.ACCESS_TOKEN + mid);
        if (StringUtils.isNotBlank(redisAccessToken)) {
            if (!accessToken.equals(redisAccessToken)) {
                throw new BizException(ErrorEnum.ACCESSTOKEN_EXPIRATION);
            }
        } else {
            // TODO: 2019/6/20 获取账户信息
            log.info("调用成功");

        }
        return ResultUtil.ok("调用成功");
    }
}
