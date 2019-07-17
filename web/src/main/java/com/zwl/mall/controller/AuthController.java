package com.zwl.mall.controller;

import com.alibaba.fastjson.JSON;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.constants.RegisterFrom;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.exception.SysException;
import com.zwl.common.utils.HostnameUtil;
import com.zwl.common.utils.StringUtil;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.system.config.wx.mp.WxMpConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date: 2019/7/10 14:49
 * @Author: 二师兄超级帅
 * @Description:
 */

@RestController
@RequestMapping("/api/pub/")
@Slf4j
@Api(tags = "登录")
public class AuthController {
    @Autowired
    private IUserBaseService iUserBaseService;
//    @Autowired
//    private IMessageService iMessageService;

//    @ApiOperation(value = "公众号授权", notes = "公众号授权")
//    @GetMapping("/authorize")
//    public Result authorize(HttpServletRequest request, @RequestParam("returnUrl") String returnUrl) {
//        StringBuffer url = request.getRequestURL();
//        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
//        log.debug(tempContextUrl);
//        String finalUrl = tempContextUrl + "api/pub/userInfo?referUid=1";
//        WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wx3b5005d9d0c0c515");
//        String redirectURL = wxMpService.oauth2buildAuthorizationUrl(finalUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
//        log.info("【微信网页授权】获取code,redirectURL={}", redirectURL);
//        return ResultUtil.ok(redirectURL);
//    }

    @GetMapping("/login")
    @ApiOperation(value = "授权登录", notes = "授权登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "微信code", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "referUid", value = "推荐人id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "registerFrom", value = "注册来源 1:h5 2:android 3:ios 4:小程序", required = false, paramType = "query", dataType = "Integer")
    })
    public Result<AccessToken> userInfo(@ApiIgnore HttpServletRequest request,
                                        @RequestParam("code") String code,
                                        @RequestParam(value = "referUid", required = false) String referUid,
                                        @RequestParam(value = "registerFrom", required = false) Integer registerFrom
    ) {
        Long mid = HostnameUtil.getMidByHostname(request);
        log.debug("【微信网页授权】code={}", code);
        log.debug("【微信网页授权】referUid={}", referUid);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wx3b5005d9d0c0c515");
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.debug("【微信网页授权】{}", e);
            throw new SysException(ErrorEnum.SYS_ERROR);
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        WxMpUser wxMpUser = null;
        try {
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        //授权登录之后先根据unionId查询是否存在该用户，不存在则保存用户信息到用户表中，存在则直接返回token
        // TODO: 2019/7/5 目前根据gzh_open_id去做匹配
        AccessToken login = iUserBaseService.login(wxMpUser, StringUtil.isBlank(referUid) ? null : Long.parseLong(referUid), null == registerFrom ? RegisterFrom.H5.getIndex() : registerFrom, mid);
        log.info(JSON.toJSONString(login));
        log.info("【微信网页授权】openId={}", openId);
        return ResultUtil.ok(login);
    }


    //    /**
//     * 登陆接口
//     */
//    @PostMapping("/login")
//    @ApiOperation(value = "小程序授权登录", notes = "小程序授权登录")
//    public String login(@RequestBody JSONObject jsonObject) {
//        String appid = jsonObject.getString("appid");
//        String code = jsonObject.getString("code");
//        if (StringUtils.isBlank(code)) {
//            return "empty jscode";
//        }
//
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//
//        try {
//            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
//            log.info(session.getSessionKey());
//            log.info(session.getOpenid());
//            //TODO 可以增加自己的逻辑，关联业务相关数据
//            return JsonUtils.toJson(session);
//        } catch (WxErrorException e) {
//            log.error(e.getMessage(), e);
//            return e.toString();
//        }
//    }
//
//    /**
//     * <pre>
//     * 获取用户信息接口
//     * </pre>
//     */
//    @GetMapping("/info")
//    @ApiOperation(value = "小程序获取用户信息接口", notes = "小程序获取用户信息接口")
//    public String info(@PathVariable String appid, String sessionKey,
//                       String signature, String rawData, String encryptedData, String iv) {
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//
//        // 用户信息校验
//        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return "user check failed";
//        }
//
//        // 解密用户信息
//        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
//
//        return JsonUtils.toJson(userInfo);
//    }
//
//    @PostMapping("/getRunInfo")
//    @ApiOperation(value = "获取微信运动信息", notes = "获取微信运动信息")
//    public String getRunInfo(@RequestBody JSONObject jsonObject) {
//        String appid = jsonObject.getString("appid");
//        String sessionKey = jsonObject.getString("sessionKey");
//        String encryptedData = jsonObject.getString("encryptedData");
//        String ivStr = jsonObject.getString("ivStr");
//        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
//        List<WxMaRunStepInfo> runStepInfo = wxService.getRunService().getRunStepInfo(sessionKey, encryptedData, ivStr);
//        return JSON.toJSONString(runStepInfo);
//    }
//
    @GetMapping("/loginWithPhoneCode")
    @ApiOperation(value = "手机验证码注册或登陆", notes = "手机验证码注册或登陆")
    public Result loginWithPhoneCode(@RequestParam("cellphone") String cellphone, @RequestParam("code") String code, @RequestParam(value = "referUid", required = false) Long referUid) {
        AccessToken accessToken = iUserBaseService.login(cellphone, code, referUid);
        return ResultUtil.ok(accessToken);
    }
//
//    @GetMapping("/sendCode")
//    @ApiOperation(value = "发送验证码", notes = "发送验证码")
//    public Result loginWithPhoneCode(@RequestParam("cellphone") String cellphone) {
//        iMessageService.sendCode(cellphone);
//        return ResultUtil.ok(Constants.HTTP_RES_CODE_200_VALUE);
//    }


}
