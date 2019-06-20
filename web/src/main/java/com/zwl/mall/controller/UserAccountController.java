package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.api.IAccessTokenService;
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
@RequestMapping("/api/out/user_account")
public class UserAccountController {

    @Autowired
    private IAccessTokenService iAccessTokenService;

    @RequestMapping("/info/{phone}/{mid}/{access_token}")
    public Result getUserAccountInfo(@PathVariable("phone") String phone,
                                     @PathVariable("mid") String mid,
                                     @PathVariable("access_token") String accesss_token
    ) {
        iAccessTokenService.check(mid, accesss_token);
        // TODO: 2019/6/20 获取账户信息
        log.info("调用成功");

        return ResultUtil.ok("调用成功");
    }
}
