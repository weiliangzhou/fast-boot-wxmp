package com.zwl.mall.system.aspect;

import com.zwl.mall.system.config.security.AspectApi;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author 二师兄
 * @since on 2018/5/10.
 */
public abstract class AbstractAspectManager implements AspectApi {

    private AspectApi aspectApi;

    public AbstractAspectManager(AspectApi aspectApi) {
        this.aspectApi = aspectApi;
    }

    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return this.aspectApi.doHandlerAspect(pjp, method);
    }

    protected abstract Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable;

}
