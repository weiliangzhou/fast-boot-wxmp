package com.zwl.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwl.common.utils.HttpClientUtil;
import com.zwl.mall.api.AsyncDataToOmexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 二师兄
 */
@Service
@Slf4j
public class AsyncDataToOmexServiceImpl implements AsyncDataToOmexService {
    @Override
    @Async("taskExecutor")
    public void sendOMEX(BigDecimal todayBTCInfo, String outOpenId) {
        Map params = new HashMap<>();
        params.put("openId", outOpenId);
        params.put("todayBTCInfo", todayBTCInfo);
        params.put("cellphone", "17682333183");
        params.put("code", "123");
        String result = HttpClientUtil.httpGetWithJSON("http://kj.yizhidao9.com/api/pub/loginWithPhoneCode", params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            log.info("调用成功" + JSON.toJSONString(params));
        } else {
            log.error("调用失败" + JSON.toJSONString(params));
        }


    }
}
