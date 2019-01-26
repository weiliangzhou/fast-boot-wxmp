package com.zwl.api2doc;

import com.terran4j.commons.api2doc.config.EnableApi2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
@SpringBootApplication
@EnableApi2Doc
public class Api2DocApplication {

    public static void main(String[] args) {
        SpringApplication.run(Api2DocApplication.class, args);
    }
}
