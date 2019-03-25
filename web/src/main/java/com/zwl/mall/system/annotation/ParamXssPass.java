package com.zwl.mall.system.annotation;


import java.lang.annotation.*;

/**
 * 在Controller方法上加入该注解不会转义参数，
 * 如果不加该注解则会：<script>alert(1)</script> --> &lt;script&gt;alert(1)&lt;script&gt;
 *
 * @author 二师兄
 * @since on 2018/6/27.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamXssPass {
}
