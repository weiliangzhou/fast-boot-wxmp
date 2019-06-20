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
 * 提取
 */
@RestController
@Slf4j
public class ExtractController {

    @Autowired
    private IAccessTokenService iAccessTokenService;

    /**
     * 1.A系统接收到B系统这边的扣款成功信息之后，再去操作转账操作，
     * 2.有可能B系统已经发出扣款成功,
     * 但是A系统没有接收到，超时了（A系统需要主动查询B提供出来的接口做一个补偿机制）
     *
     * @param phone
     * @param money
     * @param mid
     * @param accesss_token
     * @return
     */
    @RequestMapping("/reduce/{phone}/{money}/{mid}/{accesss_token}")
    public Result reduce(
            @PathVariable("phone") String phone,
            @PathVariable("money") Integer money,
            @PathVariable("mid") String mid,
            @PathVariable("accesss_token") String accesss_token
    ) {
        iAccessTokenService.check(mid, accesss_token);
        // TODO: 2019/6/20 业务逻辑
        return ResultUtil.ok("");

    }


}
