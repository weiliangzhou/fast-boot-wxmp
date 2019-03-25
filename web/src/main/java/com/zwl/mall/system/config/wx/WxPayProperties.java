package com.zwl.mall.system.config.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {
    private List<WxPayConfig> configs;

}
