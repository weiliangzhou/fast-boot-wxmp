package com.zwl.mall.system.filter;

import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.mall.api.ISysUserService;
import com.zwl.mall.dao.model.SysUser;
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
@WebFilter(filterName = "tokenFilter", urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private RedisUtil redisUtil;
    private final String TOKEN_HEADER = "Authorization";

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest h_request = (HttpServletRequest) request;
        String token = h_request.getHeader(TOKEN_HEADER);
        String requestURL = h_request.getRequestURL().toString();
        log.info("<<token>>请求url:" + requestURL + "  token:" + token);
        // TODO: 2019/6/13 过滤url 不需要登录  做一个静态list
        if (requestURL.contains("/wx")) {
            chain.doFilter(request, response);
            return;
        }
        if (StringUtils.isBlank(token)) {
            throw new BizException(ErrorEnum.LOGON_EXPIRATION);
        }
        // 这里token如果接收有空格的地方，，那就是+号没有处理好。。可以考虑变成%2B
        token = token.replaceAll(" ", "+");
        String tokenKey = redisUtil.getString(token);
        if (StringUtils.isBlank(tokenKey)) {
            throw new BizException(ErrorEnum.LOGON_EXPIRATION);
        }
        SysUser userInfo = iSysUserService.getUserInfo(tokenKey);
        if (null == userInfo) {
            throw new BizException(ErrorEnum.LOGON_EXPIRATION);
        }
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
