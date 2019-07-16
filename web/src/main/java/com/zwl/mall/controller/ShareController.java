package com.zwl.mall.controller;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.mall.controller.vo.JsApiSignatureVo;
import com.zwl.mall.system.config.wx.mp.WxMpConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 二师兄超级帅
 * @Title: ShareController
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2018/12/315:40
 */
@Api(value = "获取jsapi", tags = "获取jsapi")
@RestController
@RequestMapping("/api/pub")
@Slf4j
public class ShareController {
    @ApiOperation(value = "获取微信js执行权限")
    @GetMapping(value = "/getGzhJsApiToken", name = "获取微信js执行权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "url", required = true, paramType = "query", dataType = "string"),
    })
    public Result<JsApiSignatureVo> getGzhJsApiToken(@RequestParam("url") String url) {

        try {
            URL theUrl = new URL(url);
            log.info(theUrl.getHost());
            WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wx3b5005d9d0c0c515");
            WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(url);
            JsApiSignatureVo jsApiSignatureVo = new JsApiSignatureVo(jsapiSignature);
            return ResultUtil.ok(jsApiSignatureVo);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new BizException(ErrorEnum.WX_ERROR, e);
        } catch (MalformedURLException e) {
            throw new BizException(ErrorEnum.UNKNOWN_ERROR, e);
        }
    }
}
