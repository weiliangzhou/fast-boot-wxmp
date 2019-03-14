package com.zwl.mall.system.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 * @author 二师兄超级帅
 * @date 2019/3/7 17:00 2018/8/30 14:06
 */
public class JwtToken implements AuthenticationToken {
    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
