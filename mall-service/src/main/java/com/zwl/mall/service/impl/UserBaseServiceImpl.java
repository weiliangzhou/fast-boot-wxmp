package com.zwl.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.Constants;
import com.zwl.common.constants.RegisterFrom;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.UUIDUtil;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.IUserCalculationPowerService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.dao.mapper.UserBaseMapper;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.dao.model.UserExpand;
import com.zwl.mall.service.impl.vo.NeedInfoVo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-13
 */
@Service
@Slf4j
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements IUserBaseService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IUserCalculationPowerService iUserCalculationPowerService;
    @Autowired
    private UserBaseMapper userBaseMapper;

    private final static String USER_INFO = "USER_INFO_";
    private final static Integer NEED_10_REFER = 10;
    private final static Integer NEED_5_TIMES = 5;
    private final static Integer NEED_15_TIMES = 15;
    private final static Integer NEED_30_TIMES = 30;
    private final static Integer NEED_10000_USDT = 10000;
    private final static Integer NEED_20000_USDT = 20000;

    private final static Integer REGIST_DAYS_10 = 10;
    private final static Integer REGIST_DAYS_20 = 20;
    private final static Integer REGIST_DAYS_45 = 45;
    private final static Integer REGIST_DAYS_90 = 90;
    private final static Integer REGIST_DAYS_180 = 180;
    private final static Integer REGIST_DAYS_365 = 365;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessToken login(WxMpUser wxMpUser, Long referUid, Integer registerFrom, Long mid) {
        String headImgUrl = wxMpUser.getHeadImgUrl();
        String nickname = wxMpUser.getNickname();
        String unionId = wxMpUser.getUnionId();
        String openId = wxMpUser.getOpenId();
        Boolean subscribe = wxMpUser.getSubscribe();
        Integer sex = wxMpUser.getSex();
        String city = wxMpUser.getCity();
        String province = wxMpUser.getProvince();
        String country = wxMpUser.getCountry();
        UserBase userBaseData = selectOneByUnionId(unionId);
        //创建token
        String uuid32 = UUIDUtil.getUUID32();
        if (null == userBaseData) {
            UserBase userBase = new UserBase();
            userBase.setHeadImgUrl(headImgUrl);
            userBase.setNickName(nickname);
            userBase.setGzhOpenId(openId);
            userBase.setUnionId(unionId);
            userBase.setIsSubscribe(subscribe);
            userBase.setSex(sex);
            userBase.setRegisterFrom(registerFrom);
            userBase.setMid(mid);
            userBase.setOutOpenId(uuid32);
            userBase.insert();
            Long uid = userBase.getId();
            UserExpand expand = new UserExpand();
            expand.setUid(uid);
            expand.setCity(city);
            expand.setProvince(province);
            expand.setCountry(country);
            expand.insert();
            redisUtil.setString(uuid32, USER_INFO + mid + "_" + uid, Constants.EXRP_MONTH);
            redisUtil.setString(USER_INFO + mid + "_" + uid, JSON.toJSONString(userBase), Constants.EXRP_MONTH);
            // 邀请注册赠送邀请人100算力
            iUserCalculationPowerService.add(referUid, nickname, 1);
            return new AccessToken(uuid32, userBase);
        } else {
            String redisKey = USER_INFO + mid + "_" + userBaseData.getId();
            redisUtil.setString(uuid32, redisKey, Constants.EXRP_MONTH);
            redisUtil.setString(redisKey, JSON.toJSONString(userBaseData), Constants.EXRP_MONTH);
            return new AccessToken(uuid32, userBaseData);
        }


    }


    private UserBase selectOneByUnionId(String unionId) {
        return new UserBase().selectOne(new QueryWrapper<UserBase>().eq("union_id", unionId).eq("deleted", 0));
    }


    @Override
    public AccessToken login(String cellphone, String code, Long referUid) {
        //校验验证码
        String msgCode = redisUtil.getString(cellphone);
        if (StringUtils.isBlank(msgCode)) {
            throw new BizException(ErrorEnum.CODE_INVALID);
        } else if (!code.equals(msgCode)) {
            throw new BizException(ErrorEnum.CODE_ERROR);
        }
        //检查该手机号是否注册过   未注册则需要新增一个新用户 并且赠送120小时
        UserBase userBaseData = selectOneByCellphone(cellphone);
        //创建token
        String uuid32 = UUIDUtil.getUUID32();
        if (null == userBaseData) {
            UserBase userBase = new UserBase();
            userBase.setCellphone(cellphone);
            userBase.setOutOpenId(uuid32);
            userBase.setRegisterFrom(RegisterFrom.H5.getIndex());
            userBase.insert();
            Long uid = userBase.getId();
            redisUtil.setString(uuid32, USER_INFO + uid, Constants.EXRP_MONTH);
            redisUtil.setString(USER_INFO + uid, JSON.toJSONString(userBase), Constants.EXRP_MONTH);
            //邀请注册赠送邀请人100算力
            iUserCalculationPowerService.add(referUid, cellphone, 1);
            return new AccessToken(uuid32, userBase);
        } else {
            redisUtil.setString(uuid32, USER_INFO + userBaseData.getId(), Constants.EXRP_MONTH);
            redisUtil.setString(USER_INFO + userBaseData.getId(), JSON.toJSONString(userBaseData), Constants.EXRP_MONTH);
            return new AccessToken(uuid32, userBaseData);
        }

    }

    @Override
    public UserBase getUserInfoByOpenId(String outOpenId) {
        return new UserBase().selectOne(new QueryWrapper<UserBase>().eq("out_open_id", outOpenId).eq("deleted", 0));

    }

    @Override
    public int countRegisterDayByUid(Long uid) {
        Integer registerDay = userBaseMapper.countRegisterDayByUid(uid);
        return null == registerDay ? 0 : registerDay;
    }

    @Override
    public void checkConditons(Long uid, String openid) {
        //挖矿要求：（每到一个时间点，用户在点击挖矿页面“长按充电”按钮时，弹窗提示用户邀请好友）
        //1. 用户登录10天，要求完成邀请十个好友，才可进行继续挖矿（点击“立即邀请”按钮，弹出邀请弹窗）
        //2. 用户登录20天，要求用户交易实盘交易 5 次，可以进行继续挖矿（点击“去交易”按钮，跳转至omex交易页）
        //3. 用户登录45天，要求用户交易实盘交易 15 次，可以进行继续挖矿（点击“去交易”按钮，跳转至omex交易页）
        //4. 用户登录90天，要求用户交易实盘交易 30 次，可以进行继续挖矿（点击“去交易”按钮，跳转至omex交易页）
        //5. 用户登录180天，要求用户交易手续费累积到达 10,000 USDT ，可以进行继续挖矿（点击“去交易”按钮，跳转至omex交易页）
        //6. 用户登录365天，要求用户交易手续费累积到达 20,000 SUDT，可以进行继续挖矿（点击“去交易”按钮，跳转至omex交易页）
        //查询用户注册时间
        int registerDay = countRegisterDayByUid(uid);
        if (registerDay < REGIST_DAYS_10) {
            return;
        }
//        String result = HttpClientUtil.httpGetWithJSON(Constants.USER_INFO_URL + "?openid=" + openid, null);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        String code = jsonObject.getString("code");
//        Integer tradeOrderNum = jsonObject.getInteger("tradeOrderNum");
//        Integer counterFee = jsonObject.getInteger("counterFee");
//        if (!"0000".equals(code)) {
//            log.info("查询OMEX用户信息失败" + openid);
//        }


        Integer tradeOrderNum = 5;
        Integer counterFee = 9000;

//        1.完成邀请十个好友
        int countRefer = countByReferUid(uid);
        if (countRefer < NEED_10_REFER) {
            NeedInfoVo needInfoVo = new NeedInfoVo();
            needInfoVo.setCompletedCount(countRefer);
            needInfoVo.setNeedCount(NEED_10_REFER);
            throw new BizException(ErrorEnum.NEED_10_REFER.getCode(), JSON.toJSONString(needInfoVo));
        } else {
            if (registerDay >= REGIST_DAYS_20 && registerDay < REGIST_DAYS_45) {
                if (tradeOrderNum < NEED_5_TIMES) {
                    NeedInfoVo needInfoVo = new NeedInfoVo();
                    needInfoVo.setCompletedTimes(tradeOrderNum);
                    needInfoVo.setNeedTimes(NEED_5_TIMES);
                    throw new BizException(ErrorEnum.NEED_TRADE.getCode(), JSON.toJSONString(needInfoVo));
                }

            } else if (registerDay >= REGIST_DAYS_45 && registerDay < REGIST_DAYS_90) {
                if (tradeOrderNum < NEED_15_TIMES) {
                    NeedInfoVo needInfoVo = new NeedInfoVo();
                    needInfoVo.setCompletedTimes(tradeOrderNum);
                    needInfoVo.setNeedTimes(NEED_15_TIMES);
                    throw new BizException(ErrorEnum.NEED_TRADE.getCode(), JSON.toJSONString(needInfoVo));
                }


            } else if (registerDay >= REGIST_DAYS_90 && registerDay < REGIST_DAYS_180) {
                if (tradeOrderNum < NEED_30_TIMES) {
                    NeedInfoVo needInfoVo = new NeedInfoVo();
                    needInfoVo.setCompletedTimes(tradeOrderNum);
                    needInfoVo.setNeedTimes(NEED_30_TIMES);
                    throw new BizException(ErrorEnum.NEED_TRADE.getCode(), JSON.toJSONString(needInfoVo));
                }


            } else if (registerDay >= REGIST_DAYS_180 && registerDay < REGIST_DAYS_365) {
                if (counterFee < NEED_10000_USDT) {
                    NeedInfoVo needInfoVo = new NeedInfoVo();
                    needInfoVo.setCompletedMoney(counterFee);
                    needInfoVo.setNeedMoney(NEED_10000_USDT);
                    throw new BizException(ErrorEnum.NEED_TRADE.getCode(), JSON.toJSONString(needInfoVo));
                }

            } else if (registerDay >= REGIST_DAYS_365) {
                if (counterFee < NEED_20000_USDT) {
                    NeedInfoVo needInfoVo = new NeedInfoVo();
                    needInfoVo.setCompletedMoney(counterFee);
                    needInfoVo.setNeedMoney(NEED_20000_USDT);
                    throw new BizException(ErrorEnum.NEED_TRADE.getCode(), JSON.toJSONString(needInfoVo));
                }

            }
        }

    }

    private int countByReferUid(Long uid) {
        return userBaseMapper.countByReferUid(uid);
    }

    private UserBase selectOneByCellphone(String cellphone) {
        return new UserBase().selectOne(new QueryWrapper<UserBase>().eq("cellphone", cellphone).eq("deleted", 0));

    }


    @Override
    public UserBase getUserInfo(String tokenKey) {
        //先查询redis
        //如果不存在 则返回null
        //如果存在 则返回userInfo
        String json = redisUtil.getString(tokenKey);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, UserBase.class);
    }

}



