package com.zwl.mall.system.config.apidoc;

/**
 * @author 二师兄
 * @Title: RestApiConfig
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/4/229:46
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.show}")
    private boolean swaggerShow;


    @Bean
    public Docket ProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .enable(swaggerShow)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zwl"))
                .build()
                .apiInfo(productApiInfo());
    }

    private ApiInfo productApiInfo() {
        ApiInfo apiInfo = new ApiInfo("二师兄接口文档",
                "二师兄接口文档",
                "1.8.0",
                "API TERMS URL",
                "382308664@qq.com",
                "license",
                "license url");
        return apiInfo;
    }
}
