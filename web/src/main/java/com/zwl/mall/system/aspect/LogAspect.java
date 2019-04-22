package com.zwl.mall.system.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 二师兄超级帅
 * @Title: 日志切面
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/02/1414:05
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    private final static String upload = "upload";
    private final static String download = "download";
    private final static String login = "login";

    /**
     * 申明一个切点 里面是 execution表达式
     */
    @Pointcut("execution(public * com.zwl.mall.controller..*.*(..))")
    private void controllerAspect() {
    }

    /**
     * 请求method前打印内容
     *
     * @param joinPoint
     */
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("===============请求内容===============");
        try {
            // 打印请求内容
            log.info("请求地址:" + request.getRequestURL().toString());
            log.info("请求方式:" + request.getMethod());
            log.info("请求类方法:" + joinPoint.getSignature());
            // 过滤

            String requestUrl = request.getRequestURL().toString();
            if (!requestUrl.contains(upload)
                    && !requestUrl.contains(download)
                    && !requestUrl.contains(login)
            ) {
                log.info("请求类方法参数:" + getParams(joinPoint));
            }
        } catch (Exception e) {
            log.error("###LogAspectServiceApi.class methodBefore() ### ERROR:", e);
        }
        log.info("===============请求内容===============");
    }

    /**
     * 在方法执行完结后打印返回内容
     *
     * @param o
     */
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
//        log.info("--------------返回内容----------------");
        try {
//            log.info("Response内容:" + JSON.toJSONString(o));
        } catch (Exception e) {
            log.error("###LogAspectServiceApi.class methodAfterReturing() ### ERROR:", e);
        }
//        log.info("--------------返回内容----------------");
    }

    /**
     * 获取方法参数值并组装为JSONObject
     *
     * @param joinPoint
     * @return
     */
    private JSONObject getParams(JoinPoint joinPoint) {
        //获取参数值
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length <= 0) {
            return null;
        }
        JSONObject params = new JSONObject();
        //对象接收参数
        try {
            String data = JSON.toJSONString(joinPoint.getArgs());
            params = JSON.parseObject(data);
        }
        //普通参数传入
        catch (JSONException e) {
            //获取参数名
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
                params.put(methodSignature.getParameterNames()[i], args[i]);
            }
        }
        return params;
    }

}
