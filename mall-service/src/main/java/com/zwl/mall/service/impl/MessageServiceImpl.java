package com.zwl.mall.service.impl;

import com.zwl.common.constants.Constants;
import com.zwl.mall.api.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date: 2019/7/2 13:49
 * @Author: 二师兄超级帅
 * @Description:
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void sendCode(String cellphone) {
        redisUtil.setString(cellphone, "123", Constants.EXRP_FIVE_MINUTE);
    }
}
