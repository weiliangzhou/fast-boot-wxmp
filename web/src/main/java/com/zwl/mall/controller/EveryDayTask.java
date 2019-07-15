package com.zwl.mall.controller;

import com.zwl.common.constants.PowerCalculation;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@Slf4j
public class EveryDayTask {
    @Autowired
    private IUserCalculationPowerService iUserCalculationPowerService;
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;

    //    @Scheduled(cron = "0 */3 *  * * * ")
    public void everyDaySum() {
        log.info("开始结算挖矿收益============================>");
        LocalDateTime now =LocalDateTime.now();
//        查询过期时间大于今天00:00:00的用户


//        //今日产出
//        BigDecimal todayBtc = new BigDecimal("0");
//        UserEnergyExpireTime userEnergyExpireTime = iUserEnergyExpireTimeService.selectOneByUid(uid);
//        if (userEnergyExpireTime != null) {
//            LocalDateTime expireTime = userEnergyExpireTime.getExpireTime();
//            long beforeAbleTime = LocalDateUtil.diffSecond(LocalDateUtil.getNowBeginDate(), expireTime);
//            if (beforeAbleTime > 0) {
//                // TODO: 2019/7/5 获取当前用户算力
//                int power = iUserCalculationPowerService.getAblePowerByUid(uid);
//                todayBtc = PowerCalculation.getResult(beforeAbleTime, power);
//            }
//        }

    }
}
