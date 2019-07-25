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
    private final static String url = "https://manager2018cfd-test.ga096.cn:444/manager/mining/syncSettle";

    @Override
    @Async("taskExecutor")
    public void sendOMEX(Long flowId, BigDecimal todayBTCInfo, String outOpenId) {
        Map params = new HashMap<>();
        params.put("flowId", flowId);
        params.put("openId", outOpenId);
        params.put("amount", todayBTCInfo);
        String result = HttpClientUtil.httpGetWithJSON(url, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            log.info("调用成功" + JSON.toJSONString(params));
        } else {
            log.error("调用失败" + JSON.toJSONString(params));
        }


    }
}
