package com.zwl.mall.controller;

import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Slf4j
public class EveryDayTask {
    @Autowired
    private IUserCalculationPowerService iUserCalculationPowerService;
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;
    @Autowired
    private IUserAccountService iUserAccountService;

    //    @Scheduled(cron = "0 */3 *  * * * ")
    public void everyDaySum() {
        log.info("开始结算挖矿收益============================>");


//        BigDecimal todayBTCInfoByUid = iUserAccountService.getTodayBTCInfoByUid(uid);

    }
}
