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
    LOGON_EXPIRATION(-1, "登录过期，请重新登录"),
    ACCESSTOKEN_EXPIRATION(-10, "accesstoken无效"),
    SIGN_ERROR(-11, "签名错误");


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
