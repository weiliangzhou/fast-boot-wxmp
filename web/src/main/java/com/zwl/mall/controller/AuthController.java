package com.zwl.mall.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaRunStepInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.miniapp.utils.JsonUtils;
import com.zwl.mall.system.config.wx.miniapp.WxMaConfiguration;
import com.zwl.mall.system.config.wx.mp.WxMpConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 二师兄超级帅 on 2019/3/22.
 */
@RestController
@RequestMapping("/wx")
@Slf4j
@Api(tags = "登录")
public class AuthController {
    @Autowired
    private IUserBaseService iUserBaseService;

    @ApiOperation(value = "公众号授权", notes = "公众号授权")
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://zwl.natapp1.cc/wx/userInfo?referUid=8";
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wx9eaed98794b0c756");
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/userInfo")
    public String userInfo(
            @RequestParam("code") String code,
            @RequestParam(value = "referUid", required = false) Long referUid,
            @RequestParam("state") String returnUrl) throws Exception {
        log.info("【微信网页授权】code={}", code);
        log.info("【微信网页授权】state={}", returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wx9eaed98794b0c756");
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】{}", e);
            throw new Exception(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        //授权登录之后先根据unionId查询是否存在该用户，不存在则保存用户信息到用户表中，存在则直接返回token
        AccessToken login = iUserBaseService.login(wxMpUser, referUid);
        log.info(JSON.toJSONString(login));
        log.info("【微信网页授权】openId={}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }


    /**
     * 登陆接口
     */
    @PostMapping("/login")
    @ApiOperation(value = "小程序授权登录", notes = "小程序授权登录")
    public String login(@RequestBody JSONObject jsonObject) {
        String appid = jsonObject.getString("appid");
        String code = jsonObject.getString("code");
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    @ApiOperation(value = "小程序获取用户信息接口", notes = "小程序获取用户信息接口")
    public String info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }

    @PostMapping("/getRunInfo")
    @ApiOperation(value = "获取微信运动信息", notes = "获取微信运动信息")
    public String getRunInfo(@RequestBody JSONObject jsonObject) {
        String appid = jsonObject.getString("appid");
        String sessionKey = jsonObject.getString("sessionKey");
        String encryptedData = jsonObject.getString("encryptedData");
        String ivStr = jsonObject.getString("ivStr");
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        List<WxMaRunStepInfo> runStepInfo = wxService.getRunService().getRunStepInfo(sessionKey, encryptedData, ivStr);
        return JSON.toJSONString(runStepInfo);
    }


}
