package com.zwl.mall.system.config.wx;

import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 二师兄
 * @description 微信参数配置
 */
@Component
@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {

    @Autowired
    private WxMpProperties properties;
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();
    private static WxMpProperties wxMpProperties;

    public static Map<String, WxMpService> getMpServices() {
        return mpServices;
    }


    @Bean
    public Object services() {
        wxMpProperties = this.properties;
        mpServices = this.properties.getConfigs()
                .stream()
                .map(mpConfig -> {
                    WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
                    configStorage.setAppId(mpConfig.getMpAppId());
                    configStorage.setSecret(mpConfig.getMpAppSecret());
                    WxMpService service = new WxMpServiceImpl();
                    service.setWxMpConfigStorage(configStorage);
                    return service;
                }).collect(Collectors.toMap(s -> s.getWxMpConfigStorage().getAppId(), a -> a));

        return Boolean.TRUE;
    }


}