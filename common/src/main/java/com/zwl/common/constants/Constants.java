package com.zwl.common.constants;

public interface Constants {
    /**
     * redis过期时间，以秒为单位，一分钟
     */
    Long EXRP_MINUTE = 60L;
    /**
     * redis过期时间，以秒为单位，一分钟
     */
    Long EXRP_FIVE_MINUTE = 60L * 5;
    /**
     * redis过期时间，以秒为单位，一小时
     */
    Long EXRP_HOUR = 60 * 60L;
    /**
     * redis过期时间，以秒为单位，一天
     */
    Long EXRP_DAY = 60 * 60 * 24L;
    /**
     * redis过期时间，以秒为单位，30天
     */
    Long EXRP_MONTH = 60 * 60 * 24 * 30L;
    // 响应请求成功
    String HTTP_RES_CODE_200_VALUE = "success";
    // 系统错误
    String HTTP_RES_CODE_500_VALUE = "fail";
    // 响应请求成功code
    Integer HTTP_RES_CODE_200 = 200;
    // 系统错误
    Integer HTTP_RES_CODE_500 = 500;
    // 会员token
    String TOKEN_MEMBER = "TOKEN_MEMBER";
    // 安卓的登陆类型
    String MEMBER_LOGIN_TYPE_ANDROID = "Android";
    // IOS的登陆类型
    String MEMBER_LOGIN_TYPE_IOS = "IOS";
    // PC的登陆类型
    String MEMBER_LOGIN_TYPE_PC = "H5";
    // 用户信息不存在
    Integer HTTP_RES_CODE_NOTUSER_203 = 203;
    String OK = "OK";
    String ACCESS_TOKEN = "access_token_";


    String BTN_NAME_1 = "立即完成";
    String BTN_NAME_2 = "已完成";
}
