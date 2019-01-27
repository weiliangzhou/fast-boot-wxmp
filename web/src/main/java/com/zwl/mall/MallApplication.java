package com.zwl.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
@SpringBootApplication
@MapperScan("com.zwl.mall.dao.mapper")
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}

