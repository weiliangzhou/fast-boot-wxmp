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
import com.zwl.mall.api.IUserEnergyService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.dao.mapper.UserBaseMapper;
import com.zwl.mall.dao.model.UserBase;
import com.zwl.mall.dao.model.UserExpand;
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
    private IUserEnergyService iUserEnergyService;
    private final static String USER_INFO = "USER_INFO_";
    private final static Long taskId = 1L;

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
        log.info(JSON.toJSONString(wxMpUser));
//        UserBase userBaseData = selectOneByUnionId(unionId);
        UserBase userBaseData = selectOneByGzhOpenIdAndRegisterFrom(openId, registerFrom);
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
            // 增加120小时电力
            iUserEnergyService.add(userBase.getId(), taskId);
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

    private UserBase selectOneByGzhOpenIdAndRegisterFrom(String openId, Integer registerFrom) {
        return new UserBase().selectOne(new QueryWrapper<UserBase>().eq("gzh_open_id", openId).eq("deleted", 0).eq("register_from", registerFrom));

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
            //增加120小时电力
            iUserEnergyService.add(userBase.getId(), taskId);
            //邀请注册赠送邀请人100算力
//            iUserCalculationPowerService.add(referUid, 1);
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



