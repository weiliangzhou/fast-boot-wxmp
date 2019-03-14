package com.zwl.mall.system.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zwl.common.base.Constant;
import com.zwl.common.utils.JedisUtil;
import com.zwl.common.utils.JwtUtil;
import com.zwl.common.utils.StringUtil;
import com.zwl.mall.dao.mapper.SysPermissionMapper;
import com.zwl.mall.dao.mapper.SysRoleMapper;
import com.zwl.mall.dao.model.SysPermission;
import com.zwl.mall.dao.model.SysRole;
import com.zwl.mall.dao.model.SysUser;
import com.zwl.mall.system.config.shiro.jwt.JwtToken;
import com.zwl.mall.utils.MapUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义Realm
 *
 * @author 二师兄超级帅
 * @date 2019/3/7 17:00 2018/8/30 14:10
 */
@Service
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String account = JwtUtil.getClaim(principals.toString(), Constant.ACCOUNT);
        // 查询用户角色
        List<SysRole> roleDtos = sysRoleMapper.findRoleByUser(account);
        for (int i = 0, roleLen = roleDtos.size(); i < roleLen; i++) {
            SysRole roleDto = roleDtos.get(i);
            // 添加角色
            simpleAuthorizationInfo.addRole(roleDto.getName());
            // 根据用户角色查询权限
            List<SysPermission> sysPermissions = sysPermissionMapper.findPermissionByRole(roleDto.getName());
            for (int j = 0, perLen = sysPermissions.size(); j < perLen; j++) {
                SysPermission sysPermission = sysPermissions.get(j);
                // 添加权限
                simpleAuthorizationInfo.addStringPermission(sysPermission.getPerCode());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String account = JwtUtil.getClaim(token, Constant.ACCOUNT);
        // 帐号为空
        if (StringUtil.isBlank(account)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
        // 查询用户是否存在
        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);
        try {
            sysUser = new SysUser().selectOne(new QueryWrapper<SysUser>().allEq(MapUtil.objectToUnderlineMap(sysUser), false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sysUser == null) {
            throw new AuthenticationException("该帐号不存在(The account does not exist.)");
        }
        // 开始认证，要AccessToken认证通过，且Redis中存在RefreshToken，且两个Token时间戳一致
        if (JwtUtil.verify(token) && JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account)) {
            // 获取RefreshToken的时间戳
            String currentTimeMillisRedis = JedisUtil.getObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + account).toString();
            // 获取AccessToken时间戳，与RefreshToken的时间戳对比
            if (JwtUtil.getClaim(token, Constant.CURRENT_TIME_MILLIS).equals(currentTimeMillisRedis)) {
                return new SimpleAuthenticationInfo(token, token, "userRealm");
            }
        }
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }
}
