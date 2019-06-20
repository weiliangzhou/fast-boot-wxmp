package com.zwl.mall.api;

/**
 * Created by 二师兄超级帅 on 2019/6/20.
 */
public interface IAccessTokenService {
    /**
     * 检验api授权码合法性
     *
     * @param mid
     * @param accessToken
     */
    void check(String mid, String accessToken);
}
