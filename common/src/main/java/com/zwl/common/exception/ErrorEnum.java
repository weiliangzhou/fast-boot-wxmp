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
    REDIS_ERROR(10003, "redis错误"),
    JWT_ERROR(10004, "JWT错误"),
    IO_ERROR(10005, "IO错误"),
    QUERY_ERROR(10006, "查询失败(Query Failure)"),
    NO_ACCOUNT_ERROR(10007, "该帐号不存在(The account does not exist.)"),
    ACCOUNT_PWD_ERROR(10008, "帐号或密码错误(Account or Password Error.)"),
    KICK_OUT_ERROR(10009, "剔除失败，Account不存在(Deletion Failed. Account does not exist.)");


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
