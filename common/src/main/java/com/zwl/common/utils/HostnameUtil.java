package com.zwl.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date: 2019/7/11 13:45
 * @Author: 二师兄超级帅
 * @Description: 根据域名获取mid
 */

public class HostnameUtil {
    private static final String HOSTNAME_1 = "kj.yizhidao9.com";

    public static Long getMidByHostname(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String hostname = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
        if (hostname.contains(HOSTNAME_1)) {
            return 1L;
        }
        return 1L;
    }
}
