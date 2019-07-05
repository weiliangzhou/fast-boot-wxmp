package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.PowerCalculation;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.BigDecimalUtil;
import com.zwl.common.utils.LocalDateUtil;
import com.zwl.mall.api.IUserAccountService;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.api.IUserEnergyExpireTimeService;
import com.zwl.mall.dao.mapper.UserAccountMapper;
import com.zwl.mall.dao.model.UserAccount;
import com.zwl.mall.dao.model.UserEnergyExpireTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


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
    private IUserCalculationPowerService iUserCalculationPowerService;

    @Override
    public String getBTCInfoByUid(Long uid, boolean hasIncludeToday) {
        //可提现BTC-已经提现BTC
        BigDecimal btcInfo = userAccountMapper.getBTCInfoByUid(uid);
        if (hasIncludeToday) {
            //今日产出
            BigDecimal todayBtc = new BigDecimal("0");
            UserEnergyExpireTime userEnergyExpireTime = iUserEnergyExpireTimeService.selectOneByUid(uid);
            if (userEnergyExpireTime != null) {
                LocalDateTime expireTime = userEnergyExpireTime.getExpireTime();
                long beforeAbleTime = LocalDateUtil.diffSecond(LocalDateUtil.getNowBeginDate(), expireTime);
                if (beforeAbleTime > 0) {
                    // TODO: 2019/7/5 获取当前用户算力
                    int power = iUserCalculationPowerService.getTotalByUid(uid);
                    todayBtc = PowerCalculation.getResult(beforeAbleTime,power);
                }
            }
            return BigDecimalUtil.strAdd(btcInfo, todayBtc, 10);
        } else {
            return BigDecimalUtil.objectFormatToString(btcInfo, null);
        }


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



