package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.common.base.Constant;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.utils.AesCipherUtil;
import com.zwl.common.utils.JedisUtil;
import com.zwl.common.utils.JwtUtil;
import com.zwl.common.valid.group.UserLoginValidGroup;
import com.zwl.mall.api.ISysUserService;
import com.zwl.mall.dao.model.SysUser;
import com.zwl.mall.dao.model.vo.SysUserVo;
import com.zwl.mall.utils.MapUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 二师兄超级帅
 * @since 2019-03-14
 */

@RestController
@RequestMapping("/api")
@PropertySource("classpath:config.properties")
public class SysUserController {
    /**
     * RefreshToken过期时间
     */
    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    public ISysUserService iSysUserService;

    /**
     * 分页查询数据
     *
     * @param sysUser 查询条件
     * @return
     */
    @GetMapping("/admin/user/getPage")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public Result getPage(SysUser sysUser, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new SysUser().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<SysUser>().allEq(MapUtil.objectToUnderlineMap(sysUser), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param sysUser 传递的实体
     * @return
     */
    @GetMapping("/admin/user/getByParams")
    public Result getByParams(SysUser sysUser) throws Exception {
        return ResultUtil.ok(new SysUser().selectOne(new QueryWrapper<SysUser>().allEq(MapUtil.objectToUnderlineMap(sysUser), false)));
    }


    /**
     * 保存
     *
     * @param sysUser 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/admin/user/save")
    public Result save(SysUser sysUser) throws Exception {
        return ResultUtil.ok(sysUser.insert());
    }

    /**
     * 更新
     *
     * @param sysUser 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/admin/user/update")
    public Result update(SysUser sysUser) throws Exception {
        return ResultUtil.ok(sysUser.updateById());
    }

    /**
     * 保存或更新
     *
     * @param sysUser 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/admin/user/saveOrUpdate")
    public Result saveOrUpdate(SysUser sysUser) throws Exception {
        return ResultUtil.ok(sysUser.insertOrUpdate());
    }

    /**
     * 获取在线用户(查询Redis中的RefreshToken)
     *
     * @param
     * @return com.wang.model.common.Result
     * @author 二师兄超级帅
     * @date 2018/9/6 9:58
     */
    @GetMapping("/admin/user/online")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
    public Result online() {
        List<Object> userDtos = new ArrayList<Object>();
        // 查询所有Redis键
        Set<String> keys = JedisUtil.keysS(Constant.PREFIX_SHIRO_REFRESH_TOKEN + "*");
        for (String key : keys) {
            if (JedisUtil.exists(key)) {
                // 根据:分割key，获取最后一个字符(帐号)
                String[] strArray = key.split(":");
                SysUserVo sysUserVo = new SysUserVo();
                String account = strArray[strArray.length - 1];
                sysUserVo.setAccount(account);
                SysUser sysUser = new SysUser();
                sysUser.setAccount(account);
                try {
                    SysUser selectOne = sysUser.selectOne(new QueryWrapper<SysUser>().allEq(MapUtil.objectToUnderlineMap(sysUser), false));
                    BeanUtils.copyProperties(selectOne, sysUserVo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 设置登录时间
                sysUserVo.setLoginTime(new Date(Long.parseLong(JedisUtil.getObject(key).toString())));
                userDtos.add(sysUserVo);
            }
        }
        if (userDtos == null || userDtos.size() <= 0) {
            throw new BizException(ErrorEnum.QUERY_ERROR);
        }
        return new Result(HttpStatus.OK.value(), "查询成功(Query was successful)", userDtos);
    }

    /**
     * 登录授权
     *
     * @param sysUser
     * @return com.wang.model.common.Result
     * @author 二师兄超级帅
     * @date 2018/8/30 16:21
     */
    @PostMapping("/pub/user/login")
    public Result login(@Validated(UserLoginValidGroup.class) @RequestBody SysUser sysUser) {
        // 查询数据库中的帐号信息
        SysUser userDtoTemp = new SysUser();
        userDtoTemp.setAccount(sysUser.getAccount());
        try {
            userDtoTemp = sysUser.selectOne(new QueryWrapper<SysUser>().allEq(MapUtil.objectToUnderlineMap(userDtoTemp), false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userDtoTemp == null) {
            throw new BizException(ErrorEnum.NO_ACCOUNT_ERROR);
        }
        // 密码进行AES解密
        String key = AesCipherUtil.deCrypto(userDtoTemp.getPassword());
        // 因为密码加密是以帐号+密码的形式进行加密的，所以解密后的对比是帐号+密码
        if (key.equals(sysUser.getAccount() + sysUser.getPassword())) {
            // 清除可能存在的Shiro权限信息缓存
            if (JedisUtil.exists(Constant.PREFIX_SHIRO_CACHE + sysUser.getAccount())) {
                JedisUtil.delKey(Constant.PREFIX_SHIRO_CACHE + sysUser.getAccount());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            JedisUtil.setObject(Constant.PREFIX_SHIRO_REFRESH_TOKEN + sysUser.getAccount(), currentTimeMillis, Integer.parseInt(refreshTokenExpireTime));
            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String token = JwtUtil.sign(sysUser.getAccount(), currentTimeMillis);
//            httpServletResponse.setHeader("Authorization", token);
//            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return new Result(HttpStatus.OK.value(), "登录成功(Login Success.)", token);
        } else {
            throw new BizException(ErrorEnum.ACCOUNT_PWD_ERROR);
        }
    }

//    /**
//     * 测试登录
//     *
//     * @param
//     * @return com.wang.model.common.Result
//     * @author 二师兄超级帅
//     * @date 2018/8/30 16:18
//     */
//    @GetMapping("/article")
//    public Result article() {
//        Subject subject = SecurityUtils.getSubject();
//        // 登录了返回true
//        if (subject.isAuthenticated()) {
//            return new Result(HttpStatus.OK.value(), "您已经登录了(You are already logged in)", null);
//        } else {
//            return new Result(HttpStatus.OK.value(), "你是游客(You are guest)", null);
//        }
//    }
//
//    /**
//     * 测试登录注解(@RequiresAuthentication和subject.isAuthenticated()返回true一个性质)
//     *
//     * @param
//     * @return com.wang.model.common.Result
//     * @author 二师兄超级帅
//     * @date 2018/8/30 16:18
//     */
//    @GetMapping("/article2")
//    @RequiresAuthentication
//    public Result requireAuth() {
//        return new Result(HttpStatus.OK.value(), "您已经登录了(You are already logged in)", null);
//    }
//
//    /**
//     * 获取指定用户
//     *
//     * @param id
//     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
//     * @author 二师兄超级帅
//     * @date 2018/8/30 10:42
//     */
//    @GetMapping("/{id}")
//    @RequiresPermissions(logical = Logical.AND, value = {"user:view"})
//    public Result findById(@PathVariable("id") Integer id) {
//        UserDto userDto = userService.selectByPrimaryKey(id);
//        if (userDto == null) {
//            throw new CustomException("查询失败(Query Failure)");
//        }
//        return new Result(HttpStatus.OK.value(), "查询成功(Query was successful)", userDto);
//    }
//
//    /**
//     * 新增用户
//     *
//     * @param userDto
//     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
//     * @author 二师兄超级帅
//     * @date 2018/8/30 10:42
//     */
//    @PostMapping
//    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
//    public Result add(@Validated(UserEditValidGroup.class) @RequestBody UserDto userDto) {
//        // 判断当前帐号是否存在
//        UserDto userDtoTemp = new UserDto();
//        userDtoTemp.setAccount(userDto.getAccount());
//        userDtoTemp = userService.selectOne(userDtoTemp);
//        if (userDtoTemp != null && StringUtil.isNotBlank(userDtoTemp.getPassword())) {
//            throw new CustomUnauthorizedException("该帐号已存在(Account exist.)");
//        }
//        userDto.setRegTime(new Date());
//        // 密码以帐号+密码的形式进行AES加密
//        if (userDto.getPassword().length() > Constant.PASSWORD_MAX_LEN) {
//            throw new CustomException("密码最多8位(Password up to 8 bits.)");
//        }
//        String key = AesCipherUtil.enCrypto(userDto.getAccount() + userDto.getPassword());
//        userDto.setPassword(key);
//        int count = userService.insert(userDto);
//        if (count <= 0) {
//            throw new CustomException("新增失败(Insert Failure)");
//        }
//        return new Result(HttpStatus.OK.value(), "新增成功(Insert Success)", userDto);
//    }
//
//    /**
//     * 更新用户
//     *
//     * @param userDto
//     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
//     * @author 二师兄超级帅
//     * @date 2018/8/30 10:42
//     */
//    @PutMapping
//    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
//    public Result update(@Validated(UserEditValidGroup.class) @RequestBody UserDto userDto) {
//        // 查询数据库密码
//        UserDto userDtoTemp = new UserDto();
//        userDtoTemp.setAccount(userDto.getAccount());
//        userDtoTemp = userService.selectOne(userDtoTemp);
//        if (userDtoTemp == null) {
//            throw new CustomUnauthorizedException("该帐号不存在(Account not exist.)");
//        } else {
//            userDto.setId(userDtoTemp.getId());
//        }
//        // FIXME: 如果不一样就说明用户修改了密码，重新加密密码(这个处理不太好，但是没有想到好的处理方式)
//        if (!userDtoTemp.getPassword().equals(userDto.getPassword())) {
//            // 密码以帐号+密码的形式进行AES加密
//            if (userDto.getPassword().length() > Constant.PASSWORD_MAX_LEN) {
//                throw new CustomException("密码最多8位(Password up to 8 bits.)");
//            }
//            String key = AesCipherUtil.enCrypto(userDto.getAccount() + userDto.getPassword());
//            userDto.setPassword(key);
//        }
//        int count = userService.updateByPrimaryKeySelective(userDto);
//        if (count <= 0) {
//            throw new CustomException("更新失败(Update Failure)");
//        }
//        return new Result(HttpStatus.OK.value(), "更新成功(Update Success)", userDto);
//    }
//
//    /**
//     * 删除用户
//     *
//     * @param id
//     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
//     * @author 二师兄超级帅
//     * @date 2018/8/30 10:43
//     */
//    @DeleteMapping("/{id}")
//    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
//    public Result delete(@PathVariable("id") Integer id) {
//        int count = userService.deleteByPrimaryKey(id);
//        if (count <= 0) {
//            throw new CustomException("删除失败，ID不存在(Deletion Failed. ID does not exist.)");
//        }
//        return new Result(HttpStatus.OK.value(), "删除成功(Delete Success)", null);
//    }
//
//    /**
//     * 剔除在线用户
//     *
//     * @param id
//     * @return com.wang.model.common.Result
//     * @author 二师兄超级帅
//     * @date 2018/9/6 10:20
//     */
//    @DeleteMapping("/online/{id}")
//    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
//    public Result deleteOnline(@PathVariable("id") Integer id) {
//        UserDto userDto = userService.selectByPrimaryKey(id);
//        if (JedisUtil.exists(Constant.PREFIX_SHIRO_REFRESH_TOKEN + userDto.getAccount())) {
//            if (JedisUtil.delKey(Constant.PREFIX_SHIRO_REFRESH_TOKEN + userDto.getAccount()) > 0) {
//                return new Result(HttpStatus.OK.value(), "剔除成功(Delete Success)", null);
//            }
//        }
//        throw new CustomException("剔除失败，Account不存在(Deletion Failed. Account does not exist.)");
//    }


}
