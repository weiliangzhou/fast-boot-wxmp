package com.zwl.mall.system.filter;

import com.alibaba.fastjson.JSON;
import com.zwl.common.base.Result;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.service.impl.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 二师兄超级帅
 * @Title: TokenFilter
 * @ProjectName parent
 * @Description: token过滤器
 * @date 2018/7/615:26
 */
@Order(1)
// 重点
@WebFilter(filterName = "tokenFilter", urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Autowired
    private IUserBaseService iUserBaseService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest h_request = (HttpServletRequest) request;
        String token = request.getParameter("token");
        String requestURL = h_request.getRequestURL().toString();
        log.info("<<token>>请求url:" + requestURL + "  token:" + token);
        // TODO: 2019/6/13 过滤url 不需要登录  做一个静态list
        if (requestURL.contains("/wx")) {
            chain.doFilter(request, response);
            return;
        }
        // 验证token
        // 这里token如果接收有空格的地方，，那就是+号没有处理好。。可以考虑变成%2B
        if (StringUtils.isBlank(token)) {
            Result result = new Result(900, "请重新登录", null);
            response.getWriter().println(JSON.toJSONString(result));
            return;
        }
        token = token.replaceAll(" ", "+");
        String tokenKey = redisUtil.getString(token);
        if (StringUtils.isBlank(tokenKey)) {
            Result result = new Result(900, "请重新登录", null);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(JSON.toJSONString(result));
            return;
        } else {
            UserBase userInfo = iUserBaseService.selectOneWithCacheByUnionId(tokenKey);
            if (null == userInfo) {
                Result result = new Result(900, "请重新登录", null);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(JSON.toJSONString(result));
                return;
            } else {
                chain.doFilter(request, response);
            }
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
