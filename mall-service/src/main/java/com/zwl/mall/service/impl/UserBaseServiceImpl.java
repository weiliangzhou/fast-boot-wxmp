package com.zwl.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.common.constants.Constants;
import com.zwl.common.constants.RegisterFrom;
import com.zwl.common.utils.UUIDUtil;
import com.zwl.mall.api.IUserBaseService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.dao.mapper.UserBaseMapper;
import com.zwl.mall.dao.model.UserBase;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-13
 */
@Service
public class UserBaseServiceImpl extends ServiceImpl<UserBaseMapper, UserBase> implements IUserBaseService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public AccessToken login(WxMpUser wxMpUser) {
        String headImgUrl = wxMpUser.getHeadImgUrl();
        String nickname = wxMpUser.getNickname();
        String unionId = wxMpUser.getUnionId();
        String openId = wxMpUser.getOpenId();
        UserBase userBaseData = selectOneWithCacheByUnionId(unionId);
        //创建token
        String uuid32 = UUIDUtil.getUUID32();
        redisUtil.setString(uuid32, unionId, Constants.EXRP_MONTH);
        if (null == userBaseData) {
            UserBase userBase = new UserBase();
            userBase.setHeadImgUrl(headImgUrl);
            userBase.setNickName(nickname);
            userBase.setGzhOpenId(openId);
            userBase.setUnionId(unionId);
            userBase.setRegisterFrom(RegisterFrom.H5.getIndex());
            userBase.insert();
            redisUtil.setString(unionId, JSON.toJSONString(userBase), Constants.EXRP_MONTH);
            return new AccessToken(uuid32, userBase);
        } else {
            return new AccessToken(uuid32, userBaseData);
        }
    }


    @Override
    public UserBase selectOneWithCacheByUnionId(String unionId) {
        if (StringUtils.isBlank(unionId)) {
            return null;
        }
        try {
            //先查询redis
            String json = redisUtil.getString(unionId);
            //如果不存在则查询mysql，并放入redis
            if (StringUtils.isBlank(json)) {
                UserBase userBaseData = getUsableUser(unionId);
                if (null == userBaseData) {
                    //如果数据库不存在 则返回null
                    return null;
                } else {
                    //存入redis
                    redisUtil.setString(UUIDUtil.getUUID32(), unionId, Constants.EXRP_MONTH);
                    return userBaseData;
                }
            } else {
                UserBase userBaseData = JSON.parseObject(json, UserBase.class);
                if (null == userBaseData) {
                    //如果redis不存在 则返回null
                    UserBase usableUser = getUsableUser(unionId);
                    if (null == usableUser) {
                        //如果数据库不存在 则返回null
                        return null;
                    } else {
                        //存入redis
                        redisUtil.setString(UUIDUtil.getUUID32(), unionId, Constants.EXRP_MONTH);
                        return usableUser;
                    }
                } else {
                    return userBaseData;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserBase getUsableUser(String unionId) {
        return new UserBase().selectOne(new QueryWrapper<UserBase>().eq("union_id", unionId).eq("deleted", 0));
    }
}



