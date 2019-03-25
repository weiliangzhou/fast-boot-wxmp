package com.zwl.mall.system.config.wx;

import lombok.Data;

@Data
public class WxPayConfig {

    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String mchAppId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;
    /**
     * 回调地址
     */
    private String notifyUrl;
}
