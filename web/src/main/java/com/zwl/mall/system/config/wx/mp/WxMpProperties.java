package com.zwl.mall.system.config.wx.mp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "wx.mp")
public class WxMpProperties {
    private List<WxMpConfig> configs;

    @Data
    public static class WxMpConfig {
        private String mpAppId;
        private String mpAppSecret;
    }
}
