
package com.zwl.mall.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwl.mall.api.model.AccessToken;
import com.zwl.mall.dao.model.UserBase;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 二师兄超级帅
 * @since 2019-06-13
 */
public interface IUserBaseService extends IService<UserBase> {
    /**
     * 公众号授权登录
     *
     * @param wxMpUser
     * @return
     */
    AccessToken login(WxMpUser wxMpUser);


    /**
     * 根据unionId获取用户信息
     *
     * @param unionId
     * @return
     */
    UserBase selectOneWithCacheByUnionId(String unionId);

    /**
     * 根据token查询用户基本信息
     *
     * @param tokenKey
     * @return
     */
    UserBase getUserInfo(String tokenKey);
}
