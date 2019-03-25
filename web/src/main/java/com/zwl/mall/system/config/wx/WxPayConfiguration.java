package com.zwl.mall.system.config.wx;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(WxPayConfiguration.class);

    @Autowired
    private WxPayProperties properties;

    @Autowired
    public WxPayConfiguration(WxPayProperties properties) {
        this.properties = properties;
    }

    private static Map<String, WxPayService> services = null;

    public static WxPayService getService(String appid) {
        return services.get(appid);
    }

    @Bean
    public Map<String, WxPayService> wxService() {
        if (services == null) {
            services = new HashMap<String, WxPayService>();

            this.properties.getConfigs().stream().forEach(conf -> {
                WxPayConfig payConfig = new WxPayConfig();
                payConfig.setAppId(StringUtils.trimToNull(conf.getMchAppId()));
                payConfig.setMchId(StringUtils.trimToNull(conf.getMchId()));
                payConfig.setMchKey(StringUtils.trimToNull(conf.getMchKey()));
//                payConfig.setSubAppId(StringUtils.trimToNull(conf.getSubAppId()));
//                payConfig.setSubMchId(StringUtils.trimToNull(conf.getSubMchId()));
//                payConfig.setKeyPath(StringUtils.trimToNull(conf.getKeyPath()));

                // 可以指定是否使用沙箱环境
                payConfig.setUseSandboxEnv(false);
                WxPayService wxPayService = new WxPayServiceImpl();
                wxPayService.setConfig(payConfig);

                services.put(wxPayService.getConfig().getAppId(), wxPayService);

            });
        }
        logger.info("WxPayConfiguration initialized successfully...");

        return services;
    }

}
