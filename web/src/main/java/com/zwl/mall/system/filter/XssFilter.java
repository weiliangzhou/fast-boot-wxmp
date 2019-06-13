package com.zwl.mall.system.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author 二师兄超级帅
 * @Title: TokenFilter
 * @ProjectName parent
 * @Description: 防止xss攻击过滤器
 * @date 2018/7/615:26
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {

    }

}
