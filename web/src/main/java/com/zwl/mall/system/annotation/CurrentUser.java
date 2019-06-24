package com.zwl.mall.system.annotation;

import java.lang.annotation.*;

/**
 * Created by 二师兄超级帅 on 2019/6/24.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}