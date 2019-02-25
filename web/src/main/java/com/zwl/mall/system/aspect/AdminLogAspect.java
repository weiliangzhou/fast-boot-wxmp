package com.zwl.mall.system.aspect;

import com.zwl.mall.api.ISysLogService;
import com.zwl.mall.system.annotation.AdminLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 二师兄超级帅
 * @Title: 后台操作日志记录
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/2/2010:18
 */
@Aspect
@Component
public class AdminLogAspect {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 日志切入点
     */
    @Pointcut("@annotation(com.zwl.mall.system.annotation.AdminLog)")
    public void logPointCut() {
    }

    @AfterReturning(pointcut = "logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        /**
         * 解析Log注解
         */
        String methodName = joinPoint.getSignature().getName();
        Method method = currentMethod(joinPoint, methodName);
        AdminLog adminLog = method.getAnnotation(AdminLog.class);
        sysLogService.put(joinPoint, methodName, adminLog.module(), adminLog.description());
    }

    /**
     * 获取当前执行的方法
     *
     * @param joinPoint  连接点
     * @param methodName 方法名称
     * @return 方法
     */
    private Method currentMethod(JoinPoint joinPoint, String methodName) {
        /**
         * 获取目标类的所有方法，找到当前要执行的方法
         */
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

}
