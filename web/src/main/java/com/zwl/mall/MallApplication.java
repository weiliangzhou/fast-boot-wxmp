package com.zwl.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
@SpringBootApplication
@ComponentScan("com.zwl.*")
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}

