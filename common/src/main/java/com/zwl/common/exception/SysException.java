package com.zwl.common.exception;

/**
 * @author 二师兄超级帅
 * @Title: SysException
 * @ProjectName shirojwtdemo
 * @Description: TODO
 * @date 2019/3/1415:11
 */
public class SysException extends RuntimeException {
    private int code;
    private String msg;

    public SysException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public SysException(int code, String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public SysException(ErrorEnum err) {
        super(err.getMsg());
        this.msg = err.getMsg();
        this.code = err.getCode();
    }

    public SysException(ErrorEnum err, Exception e) {
        super(err.getMsg(), e);
        this.msg = err.getMsg();
        this.code = err.getCode();
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
