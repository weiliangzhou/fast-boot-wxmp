package com.zwl.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwl.mall.api.ISysUserService;
import com.zwl.mall.dao.mapper.SysUserMapper;
import com.zwl.mall.dao.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public SysUser getUserInfo(String tokenKey) {
        //先查询redis
        //如果不存在 则返回null
        //如果存在 则返回userInfo
        String json = redisUtil.getString(tokenKey);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, SysUser.class);
    }
}



