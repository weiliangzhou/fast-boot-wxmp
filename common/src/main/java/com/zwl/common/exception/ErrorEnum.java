package com.zwl.common.exception;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
public enum ErrorEnum {
    //系统错误
    UNKNOWN_ERROR(10000, "未知错误"),
    ARGUMENT_ERROR(10001, "参数错误"),
    SYS_ERROR(10002, "系统错误"),
    IO_ERROR(10003, "IO错误"),
    WX_ERROR(10004, "微信返回错误"),

    //用户错误
    IS_TODAY_COMPLETED(1001, ""),
    IS_ONCE_COMPLETED(1002, "不能重新完成任务"),
    CODE_INVALID(1003, "验证码无效"),
    CODE_ERROR(1004, "验证码错误"),
    LOW_POWER(1005, "电力不足"),
    LOW_FULL(1006, "电力充沛，不需要充电"),
    BALANCE_INSUFFICIENT(1007, "余额不足"),
    NO_USER(1008, "用户不存在"),
    NEED_10_REFER(1009, "需要邀请X好友"),
    NEED_TRADE_TIMES(1010, "需要X交易次数"),
    NEED_TRADE_MONEY(1011, "需要X交易手续费"),


    LOGON_EXPIRATION(-1, "登录过期，请重新登录"),
    ACCESS_TOKEN_EXPIRATION(-10, "accesstoken无效"),
    SIGN_ERROR(-11, "签名错误"),
    MERCHANT_ERROR(-12, "商户信息有误");


    private int code;
    private String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
