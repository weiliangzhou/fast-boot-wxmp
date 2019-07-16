package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.BigDecimalUtil;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserAccountMapper;
import com.zwl.mall.dao.model.UserAccount;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-07-01
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private IUserEnergyExpireTimeService iUserEnergyExpireTimeService;

    @Override
    public String getBTCInfoByUid(Long uid, boolean hasIncludeToday) {
        //可提现BTC-已经提现BTC
        BigDecimal btcInfo = userAccountMapper.getBTCInfoByUid(uid);
        if (hasIncludeToday) {
            BigDecimal todayBtc = getTodayBTCInfoByUid(uid);
            return BigDecimalUtil.strAdd(btcInfo, todayBtc, 10);
        } else {
            return BigDecimalUtil.objectFormatToString(btcInfo, null);
        }


    }

    @Override
    public BigDecimal getTodayBTCInfoByUid(Long uid) {
        //今日产出
        BigDecimal todayBtc = new BigDecimal("0");
        long needSeconds = 0;
        LocalDateTime finalStartTime = null;
        LocalDateTime finalEndTime = null;
        List<UserEnergyExpireTime> userEnergyExpireTimeList = iUserEnergyExpireTimeService.selectTodayListByUid(uid);
        for (UserEnergyExpireTime userEnergyExpireTime : userEnergyExpireTimeList) {
            //如果start_time小于今天的0点则按照0点计算
            LocalDateTime startTime = userEnergyExpireTime.getStartTime();
            LocalDateTime todayBeginTime = LocalDateUtil.getNowBeginDate();
            LocalDateTime endTime = userEnergyExpireTime.getEndTime();
            LocalDateTime now = LocalDateTime.now();
            if (startTime.isBefore(todayBeginTime)) {
                finalStartTime = todayBeginTime;
            } else {
                //如果end_time>现在则按照end_time计算
                if (endTime.isAfter(now)) {
                    finalEndTime = now;
                } else {
                    finalEndTime = endTime;
                }

            }
            needSeconds = LocalDateUtil.diffSecond(finalStartTime, finalEndTime);
            todayBtc = BigDecimalUtil.bigMul3(userEnergyExpireTime.getCalculationPower(), new BigDecimal(needSeconds), 10);
            todayBtc = todayBtc.add(todayBtc);
        }
        return todayBtc;
    }

    @Override
    public void reduce(Long uid, BigDecimal money) {
        // TODO: 2019/7/4
        //可提现BTC-已经提现BTC
        BigDecimal currentBTC = userAccountMapper.getBTCInfoByUid(uid);
        boolean bigCompareTo = BigDecimalUtil.bigCompareTo(money, currentBTC);
        if (bigCompareTo) {
            throw new BizException(ErrorEnum.BALANCE_INSUFFICIENT);
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setUid(uid);
        userAccount.setMoney(money);
        userAccount.setType(-1);
        userAccount.insert();
    }

}



