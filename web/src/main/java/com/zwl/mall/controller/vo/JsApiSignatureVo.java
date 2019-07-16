package com.zwl.mall.controller.vo;

import lombok.Data;
import me.chanjar.weixin.common.bean.WxJsapiSignature;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 二师兄超级帅
 * @Title: JsApiSignatureVo
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2018/12/310:13
 */

@Data
public class JsApiSignatureVo {
    /**
     * JSAPI权限列表
     */
    public static List<String> jsApiLists = new ArrayList<>();
    private WxJsapiSignature wxJsapiSignature;
    /**
     * 权限列表
     */
    private List<String> jsApiList;

    public JsApiSignatureVo(WxJsapiSignature wxJsapiSignature) {
        this.wxJsapiSignature = wxJsapiSignature;
        this.jsApiList = jsApiLists;
    }

    static {
        jsApiLists.add("onMenuShareTimeline");
        jsApiLists.add("onMenuShareAppMessage");
        jsApiLists.add("onMenuShareQQ");
        jsApiLists.add("onMenuShareWeibo");
        jsApiLists.add("onMenuShareQZone");
        jsApiLists.add("startRecord");
        jsApiLists.add("stopRecord");
        jsApiLists.add("onVoiceRecordEnd");
        jsApiLists.add("playVoice");
        jsApiLists.add("pauseVoice");
        jsApiLists.add("stopVoice");
        jsApiLists.add("onVoicePlayEnd");
        jsApiLists.add("uploadVoice");
        jsApiLists.add("downloadVoice");
        jsApiLists.add("chooseImage");
        jsApiLists.add("previewImage");
        jsApiLists.add("uploadImage");
        jsApiLists.add("downloadImage");
        jsApiLists.add("translateVoice");
        jsApiLists.add("getNetworkType");
        jsApiLists.add("openLocation");
        jsApiLists.add("getLocation");
        jsApiLists.add("hideOptionMenu");
        jsApiLists.add("showOptionMenu");
        jsApiLists.add("hideMenuItems");
        jsApiLists.add("showMenuItems");
        jsApiLists.add("hideAllNonBaseMenuItem");
        jsApiLists.add("showAllNonBaseMenuItem");
        jsApiLists.add("closeWindow");
        jsApiLists.add("scanQRCode");
        jsApiLists.add("chooseWXPay");
        jsApiLists.add("openProductSpecificView");
        jsApiLists.add("addCard");
        jsApiLists.add("chooseCard");
        jsApiLists.add("openCard");
    }
}
