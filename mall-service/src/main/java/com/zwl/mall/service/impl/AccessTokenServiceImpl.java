package com.zwl.mall.service.impl;

import com.zwl.common.constants.Constants;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.mall.api.IAccessTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 二师兄超级帅 on 2019/6/20.
 */
@Service
public class AccessTokenServiceImpl implements IAccessTokenService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void check(String mid, String accessToken) {
        if (StringUtils.isBlank(mid)) {
            throw new BizException(ErrorEnum.ACCESS_TOKEN_EXPIRATION);
        }
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(mid)) {
            throw new BizException(ErrorEnum.ACCESS_TOKEN_EXPIRATION);
        }

        //校验accessToken
        String redisAccessToken = redisUtil.getString(Constants.ACCESS_TOKEN + mid);
        if (StringUtils.isNotBlank(redisAccessToken)) {
            if (!accessToken.equals(redisAccessToken)) {
                throw new BizException(ErrorEnum.ACCESS_TOKEN_EXPIRATION);
            }
        }
    }
}
