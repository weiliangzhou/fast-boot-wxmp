package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.BigDecimalUtil;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserAccountMapper;
import com.zwl.mall.dao.model.UserAccount;
import com.zwl.mall.dao.model.UserBase;
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
    @Autowired
    private IUserBaseService iUserBaseService;

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
        List<UserEnergyExpireTime> userEnergyExpireTimeList = iUserEnergyExpireTimeService.selectTodayListByUid(uid);
        if (userEnergyExpireTimeList != null) {
            for (UserEnergyExpireTime userEnergyExpireTime : userEnergyExpireTimeList) {
                //如果start_time小于今天的0点则按照0点计算
                LocalDateTime startTime = userEnergyExpireTime.getStartTime();
                LocalDateTime todayBeginTime = LocalDateUtil.getNowBeginDate();
                LocalDateTime endTime = userEnergyExpireTime.getEndTime();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime finalStartTime = startTime;
                LocalDateTime finalEndTime = endTime;
                if (startTime.isBefore(todayBeginTime)) {
                    finalStartTime = todayBeginTime;
                } else {
                    //如果end_time>现在则按照end_time计算
                    if (endTime.isAfter(now)) {
                        finalEndTime = now;
                        needSeconds = LocalDateUtil.diffSecond(finalStartTime, finalEndTime);
                        BigDecimal currentBtc = BigDecimalUtil.bigMul3(userEnergyExpireTime.getCalculationPower(), new BigDecimal(needSeconds), 10);
                        todayBtc = todayBtc.add(currentBtc);
                        return todayBtc;
                    }

                }
                needSeconds = LocalDateUtil.diffSecond(finalStartTime, finalEndTime);
                BigDecimal currentBtc = BigDecimalUtil.bigMul3(userEnergyExpireTime.getCalculationPower(), new BigDecimal(needSeconds), 10);
                todayBtc = todayBtc.add(currentBtc);
            }
        }
        return todayBtc;
    }

    @Override
    public BigDecimal getYesterdayBTCInfoByUid(Long uid) {
        //今日产出
        BigDecimal todayBtc = new BigDecimal("0");
        long needSeconds = 0;
        List<UserEnergyExpireTime> userEnergyExpireTimeList = iUserEnergyExpireTimeService.selectYesterdayListByUid(uid);
        if (userEnergyExpireTimeList != null) {
            for (UserEnergyExpireTime userEnergyExpireTime : userEnergyExpireTimeList) {
                //如果start_time小于昨天的0点则按照0点计算
                LocalDateTime startTime = userEnergyExpireTime.getStartTime();
                LocalDateTime todayBeginTime = LocalDateUtil.getNowBeginDate();
                LocalDateTime yesterdayBeginTime = LocalDateUtil.add(LocalDateUtil.getNowBeginDate(), 0, 0, -1, 0, 0, 0);
                LocalDateTime endTime = userEnergyExpireTime.getEndTime();
                LocalDateTime finalStartTime = startTime;
                LocalDateTime finalEndTime = endTime;
                if (startTime.isBefore(yesterdayBeginTime) && endTime.isAfter(yesterdayBeginTime)) {
                    finalStartTime = yesterdayBeginTime;
                } else {
                    //如果end_time>todayBeginTime则按照end_time计算
                    if (endTime.isAfter(todayBeginTime)) {
                        finalEndTime = todayBeginTime;
                        needSeconds = LocalDateUtil.diffSecond(finalStartTime, finalEndTime);
                        BigDecimal currentBtc = BigDecimalUtil.bigMul3(userEnergyExpireTime.getCalculationPower(), new BigDecimal(needSeconds), 10);
                        todayBtc = todayBtc.add(currentBtc);
                        return todayBtc;
                    }
                }
                needSeconds = LocalDateUtil.diffSecond(finalStartTime, finalEndTime);
                BigDecimal currentBtc = BigDecimalUtil.bigMul3(userEnergyExpireTime.getCalculationPower(), new BigDecimal(needSeconds), 10);
                todayBtc = todayBtc.add(currentBtc);
            }
        }

        return todayBtc;
    }

    @Override
    public boolean isTodayComplete(Long uid) {
        int todayComplete = userAccountMapper.isTodayComplete(uid);
        return todayComplete > 0 ? true : false;
    }


    @Override
    public void reduce(String openid, String money, String outTradeNo) {

        UserBase userBase = iUserBaseService.getUserInfoByOpenId(openid);
        if (userBase == null) {
            throw new BizException(ErrorEnum.NO_USER);
        }

        Long uid = userBase.getId();
        BigDecimal inputMoney = new BigDecimal(money);
        //可提现BTC-已经提现BTC
        BigDecimal currentBTC = userAccountMapper.getBTCInfoByUid(uid);
        boolean bigCompareTo = BigDecimalUtil.bigCompareTo(inputMoney, currentBTC);
        if (bigCompareTo) {
            throw new BizException(ErrorEnum.BALANCE_INSUFFICIENT);
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setUid(uid);
        userAccount.setMoney(inputMoney);
        userAccount.setType(-1);
        userAccount.setOutOpenId(openid);
        userAccount.setOutTradeNo(outTradeNo);
        userAccount.insert();
    }

}



