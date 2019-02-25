package com.zwl.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.ISysLogService;
import com.zwl.mall.dao.mapper.SysLogMapper;
import com.zwl.mall.dao.model.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-02-25
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
    private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s";
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void put(JoinPoint joinPoint, String methodName, String module, String description) {
        SysLog sysLog = new SysLog();
        // 获取当前登录用户
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (null != authentication) {
//            if (authentication instanceof AuthenticationToken) {
//                // 获取当前登录用户
//                AuthenticationToken auth = (AuthenticationToken) authentication;
//                String username = auth.getAccessToken().getUserInfo().getUsername();
//                sysLog.setOperator(username);
//            }
//        }
        sysLog.setModule(module);
        sysLog.setDescription(description);
        sysLog.setContent(operateContent(joinPoint, methodName));
        sysLogMapper.insert(sysLog);
    }

    private String operateContent(JoinPoint joinPoint, String methodName) {
        String className = joinPoint.getTarget().getClass().getName();
        JSONObject paramsStr = getParams(joinPoint);
        String formatStr = String.format(LOG_CONTENT, className, methodName, paramsStr);
        if (formatStr.length() > 1000) {
            formatStr = formatStr.substring(0, 999);
        }
        return formatStr;
    }

    private JSONObject getParams(JoinPoint joinPoint) {
        //获取参数值
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length <= 0) {
            return null;
        }
        JSONObject params = new JSONObject();
        //对象接收参数
        try {
            String data = JSON.toJSONString(args);
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



