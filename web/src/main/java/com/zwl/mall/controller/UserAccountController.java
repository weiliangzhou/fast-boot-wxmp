package com.zwl.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.utils.SignUtil;
import com.zwl.mall.api.IAccessTokenService;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.system.annotation.CurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 二师兄超级帅 on 2019/6/20.
 */
@RestController
@Slf4j
@Api(value = "账户管理", tags = "账户管理")
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private IAccessTokenService iAccessTokenService;

    @Autowired
    private IUserAccountService iUserAccountService;

    @ApiOperation(value = "第三方获取账户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonObject", value = "用户数据", required = true, paramType = "body", dataType = "JSONObject")
    })
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


    @PostMapping("/out/user_account/info")
    public Result getUserAccountInfo(@CurrentUser UserBase userBase, @RequestBody JSONObject jsonObject
    ) {
        String access_token = jsonObject.getString("access_token");
        String mid = jsonObject.getString("mid");
        iAccessTokenService.check(mid, access_token);
        log.info(JSON.toJSONString(userBase));
        // TODO: 2019/6/20 获取账户信息
        //可提现的btc-已经提现的btc+今天
        log.info("调用成功");

        return ResultUtil.ok("调用成功");
    }

    /**
     * 1.A系统接收到B系统这边的扣款成功信息之后，再去操作转账操作，
     * 2.有可能B系统已经发出扣款成功,
     * 但是A系统没有接收到，超时了（A系统需要主动查询B提供出来的接口做一个补偿机制）
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "第三方对应账户减少", notes = "sign=(data+accesss_token)通过工具类加密生成")
    @ApiImplicitParam(name = "jsonObject", value = "{\n" +
            "\t\"data\": {\n" +
            "\t\t\"phone\": \"17682333183\",\n" +
            "\t\t\"money\": \"0.0000219\"\n" +
            "\t},\n" +
            "\t\"mid\": \"kj\",\n" +
            "\t\"accesss_token\": \"dsdasdas\",\n" +
            "\t\"sign\": \"dsfsd\"\n" +
            "}", dataType = "string", paramType = "path")
    @PostMapping("/out/user_account/reduce")
    public Result reduce(@RequestBody JSONObject jsonObject) {
        String data = jsonObject.getString("data");
        String access_token = jsonObject.getString("access_token");
        String mid = jsonObject.getString("mid");
        String sign = jsonObject.getString("sign");
        iAccessTokenService.check(mid, access_token);
        SignUtil.checkSign(data, access_token, sign);
        // TODO: 2019/6/20 业务逻辑
        return ResultUtil.ok("");

    }
}
