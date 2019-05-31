package com.zwl.mall.controller;

import com.zwl.mall.system.config.wx.WxMpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * Created by 二师兄超级帅 on 2019/3/22.
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class AuthController {

//    /**
//     * @Description: 微信授权
//     * @Param: [returnUrl]
//     * @returns: java.lang.String
//     * @Author: 二师兄
//     * @Date: 2018/8/11 15:08
//     */
//    @GetMapping("/authorize")
//    public String authorize(@RequestParam("returnUrl") String returnUrl) {
//        String url = "zwl.natapp1.cc/api/auth/userInfo";
//        WxMpService mpService = WxMpConfiguration.getMpServices().get("wx9eaed98794b0c756");
//        String redirectUrl = mpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
//        log.info("【微信网页授权】获取code,redirectUrl={}", redirectUrl);
//        return "redirect:" + redirectUrl;
//    }
//
//    /**
//     * @Description: 微信授权回调用户信息
//     * @Param: [code, returnUrl]
//     * @returns: java.lang.String
//     * @Author: 二师兄
//     * @Date: 2018/8/11 15:08
//     */
//    @GetMapping("/userInfo")
//    public String userInfo(@RequestParam("code") String code,
//                           @RequestParam("state") String returnUrl) {
//        log.info("【微信网页授权】code={}", code);
//        log.info("【微信网页授权】state={}", returnUrl);
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
//        try {
//            WxMpService mpService = WxMpConfiguration.getMpServices().get("wx9eaed98794b0c756");
//            wxMpOAuth2AccessToken = mpService.oauth2getAccessToken(code);
//            WxMpUser wxMpUser = mpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
//            String headImgUrl = wxMpUser.getHeadImgUrl();
//            String nickname = wxMpUser.getNickname();
//            String openid = wxMpUser.getOpenId();
//            log.debug("【微信网页授权获】获取用户信息：{}", wxMpUser);
//        } catch (WxErrorException e) {
//            e.printStackTrace();
//            log.error("【微信网页授权】{}", e);
//        }
//        String openId = wxMpOAuth2AccessToken.getOpenId();
//        log.info("【微信网页授权】openId={}", openId);
//        return "redirect:" + returnUrl + "?openid=" + openId;
//    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        String url = "http://zwl.natapp1.cc/wechat/userInfo";
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
        String headImgUrl = wxMpUser.getHeadImgUrl();
        String nickname = wxMpUser.getNickname();
        log.info(headImgUrl);
        log.info(nickname);
        log.info(openId);
        log.info("【微信网页授权】openId={}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }


}
