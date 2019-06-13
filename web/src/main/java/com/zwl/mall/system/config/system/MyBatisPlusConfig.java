package com.zwl.mall.system.config.system;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 二师兄
 * @Title: MyBatisPlusConfig
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/6/1310:03
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
