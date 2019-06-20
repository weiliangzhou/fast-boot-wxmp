package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.Constants;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.UUIDUtil;
import com.zwl.mall.service.impl.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 二师兄超级帅 on 2019/6/20.
 */
@RestController
@Slf4j
@RequestMapping("/api/pub")
public class AccessTokenController {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据mid，pwd获取accessToken
     *
     * @param mid
     * @param pwd
     * @return
     */
    @RequestMapping("/access_token/{mid}/{pwd}")
    public Result getAccessTokenByMidAndPwd(@PathVariable("mid") String mid,
                                            @PathVariable("pwd") String pwd) {

        // TODO: 2019/6/20 判断帐号密码
        if ("kj".equals(mid) && "123".equals(pwd)) {
            String uuid32 = UUIDUtil.getUUID32();
            redisUtil.setString(Constants.ACCESS_TOKEN + mid, uuid32, Constants.EXRP_HOUR * 2);
            return ResultUtil.ok(uuid32);
        }
        throw new BizException(ErrorEnum.ACCESSTOKEN_EXPIRATION);

    }
}
