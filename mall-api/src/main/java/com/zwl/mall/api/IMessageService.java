package com.zwl.mall.api;

/**
 * @Date: 2019/7/2 13:49
 * @Author: 二师兄超级帅
 * @Description:
 */
public interface IMessageService {
    /**
     * 发送验证码
     *
     * @param cellphone
     */
    void sendCode(String cellphone);
}
