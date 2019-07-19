
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
     * @param referUid
     * @param registerFrom
     * @param mid
     * @return
     */
    AccessToken login(WxMpUser wxMpUser, Long referUid, Integer registerFrom, Long mid);


    /**
     * 根据token查询用户基本信息
     *
     * @param tokenKey
     * @return
     */
    UserBase getUserInfo(String tokenKey);

    /**
     * 手机验证码注册或登陆
     *
     * @param cellphone
     * @param code
     * @param referUid
     * @return
     */
    AccessToken login(String cellphone, String code, Long referUid);

    /**
     * 根据outOpenId查询用户
     *
     * @param outOpenId
     */
    UserBase getUserInfoByOpenId(String outOpenId);
}
