package com.zwl.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwl.common.utils.HttpClientUtil;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.model.UserAccount;
import com.zwl.mall.dao.model.UserBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
@Slf4j
public class EveryDayTask {
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;
    @Autowired
    private IUserAccountService iUserAccountService;
    @Autowired
    private IUserBaseService iUserBaseService;

    //每隔1分钟执行一次
    @Scheduled(cron = "0 */1 * * * ?")
    //每天凌晨1点执行一次：0 0 1 * * ?
//    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void everyDaySum() {
        log.info("开始结算挖矿收益============================>");
        List<Long> uidList = iUserEnergyExpireTimeService.listTodayUid();
        log.info(JSON.toJSONString(uidList));
        for (Long uid : uidList) {
            BigDecimal todayBTCInfo = iUserAccountService.getYesterdayBTCInfoByUid(uid);
//            log.info("==============>" + uid);
//            log.info("==============>" + todayBTCInfo);
            if (!todayBTCInfo.equals(BigDecimal.ZERO)) {
                //先查询昨天是否结算过
                boolean todayComplete = iUserAccountService.isTodayComplete(uid);
                if (todayComplete) {
                    continue;
                }
                UserBase userBase = iUserBaseService.getById(uid);
                String outOpenId = userBase.getOutOpenId();
                UserAccount userAccount = new UserAccount();
                userAccount.setUid(uid);
                userAccount.setMoney(todayBTCInfo);
                userAccount.setType(1);
                userAccount.setOutOpenId(outOpenId);
                userAccount.insert();
                // TODO: 2019/7/18 异步通讯给omex
                sendOMEX(todayBTCInfo, outOpenId);

            }
        }

    }

    @Async("taskExecutor")
    void sendOMEX(BigDecimal todayBTCInfo, String outOpenId) {
        Map params = new HashMap<>();
        params.put("openId", outOpenId);
        params.put("todayBTCInfo", todayBTCInfo);
        String result = HttpClientUtil.httpGetWithJSON("http://kj.yizhidao9.com/api/pub/getGzhJsApiToken", params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger("code");
        if (code == 0) {
            log.info("调用成功" + JSON.toJSONString(params));
        } else {
            log.error("调用失败" + JSON.toJSONString(params));
        }

    }
}
