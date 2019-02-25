package com.zwl.mall.system.annotation;

import java.lang.annotation.*;

/**
 * @author 二师兄超级帅
 * @Title: 后台操作日志记录
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/2/2010:17
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminLog {
    /**
     * 模块
     */
    String module() default "";

    /**
     * 描述
     */
    String description() default "";
}
