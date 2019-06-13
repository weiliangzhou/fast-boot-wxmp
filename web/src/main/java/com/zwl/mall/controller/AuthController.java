package com.zwl.mall.controller;

import com.alibaba.fastjson.JSON;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.system.config.wx.mp.WxMpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * Created by 二师兄超级帅 on 2019/3/22.
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class AuthController {
    @Autowired
    private IUserBaseService iUserBaseService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://zwl.natapp1.cc/wx/userInfo";
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wx9eaed98794b0c756");
        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
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
        AccessToken login = iUserBaseService.login(wxMpUser);
        log.info(JSON.toJSONString(login));
        log.info("【微信网页授权】openId={}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }


}
