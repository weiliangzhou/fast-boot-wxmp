package com.zwl.mall.system.config.security;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author 二师兄 装饰器模式
 * @since on 2018/5/10.
 */
public interface AspectApi {

    Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable;

}
