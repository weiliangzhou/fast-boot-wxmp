package com.zwl.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.Constants;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.exception.SysException;
import com.zwl.common.utils.SignUtil;
import com.zwl.mall.api.IAccessTokenService;
import com.zwl.mall.api.IUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Date: 2019/7/3 11:42
 * @Author: 二师兄超级帅
 * @Description: 账户管理
 */
@RestController
@Slf4j
@Api(value = "api对外接口", tags = "api对外接口")
@RequestMapping("/api/out/user_account")
public class ApiOutController {
    @Autowired
    private IAccessTokenService iAccessTokenService;
    @Autowired
    private IUserAccountService iUserAccountService;

    @PostMapping("/info")
    @ApiOperation(value = "账户查询", notes = "")
    @ApiImplicitParam(name = "jsonObject", value = "{\n" +
            "\t\"data\": {\n" +
            "\t\t\"openid\": \"1\"},\n" +
            "\t\"mid\": \"kj\"" +
            "}", dataType = "string", paramType = "path")
    public Result getUserAccountInfo(@RequestBody JSONObject jsonObject
    ) {
//        String access_token = jsonObject.getString("accessToken");
        String mid = jsonObject.getString("mid");
        String data = jsonObject.getString("data");
        String sign = jsonObject.getString("sign");
//        iAccessTokenService.check(mid, access_token);
//        SignUtil.checkSign(data, access_token, sign);
        Long openid = jsonObject.getJSONObject("data").getLong("openid");
        if (openid == null) {
            throw new SysException(ErrorEnum.ARGUMENT_ERROR);
        }
        // TODO: 2019/6/20 获取账户信息
        //可提现BTC-已经提现BTC
        String btcInfoByUid = iUserAccountService.getBTCInfoByUid(openid, false);
        log.debug("查询成功");
        return ResultUtil.ok(btcInfoByUid);
    }

    /**
     * 1.A系统接收到B系统这边的扣款成功信息之后，再去操作转账操作，
     * 2.有可能B系统已经发出扣款成功,
     * 但是A系统没有接收到，超时了（A系统需要主动查询B提供出来的接口做一个补偿机制）
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "第三方对应账户减少", notes = "")
    @ApiImplicitParam(name = "jsonObject", value = "{\n" +
            "\t\"data\": {\n" +
            "\t\t\"phone\": \"17682333183\",\n" +
            "\t\t\"money\": \"0.0000219\"\n" +
            "\t},\n" +
            "\t\"mid\": \"kj\"" +
//            "\t\"accessToken\": \"dsdasdas\",\n" +
//            "\t\"sign\": \"dsfsd\"\n" +
            "}", dataType = "string", paramType = "path")
    @PostMapping("/reduce")
    public Result reduce(@RequestBody JSONObject jsonObject) {
        String data = jsonObject.getString("data");
        String accessToken = jsonObject.getString("accessToken");
        String mid = jsonObject.getString("mid");
        String sign = jsonObject.getString("sign");
        iAccessTokenService.check(mid, accessToken);
        SignUtil.checkSign(data, accessToken, sign);
        JSONObject dataJson = jsonObject.getJSONObject("data");
        Long openid = dataJson.getLong("openid");
        BigDecimal money = dataJson.getBigDecimal("money");
        iUserAccountService.reduce(openid, money);
        return ResultUtil.ok(Constants.HTTP_RES_CODE_200_VALUE);

    }
}
