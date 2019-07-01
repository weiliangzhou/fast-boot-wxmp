package com.zwl.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.PowerCalculation;
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
    public String getBTCInfoByUid(Long uid) {
        //可提现BTC-已经提现BTC
        BigDecimal btcInfo = userAccountMapper.getBTCInfoByUid(uid);
        //今日产出
        BigDecimal todayBtc = new BigDecimal("0");
        UserEnergyExpireTime userEnergyExpireTime = iUserEnergyExpireTimeService.selectOneByUid(uid);
        if (userEnergyExpireTime != null) {
            LocalDateTime expireTime = userEnergyExpireTime.getExpireTime();
            long beforeAbleTime = LocalDateUtil.diffSecond(LocalDateUtil.getNowBeginDate(), expireTime);
            if (beforeAbleTime > 0) {
                todayBtc = PowerCalculation.getResult(beforeAbleTime);
            }
        }
        return BigDecimalUtil.strAdd(btcInfo, todayBtc, 10);
    }
}



