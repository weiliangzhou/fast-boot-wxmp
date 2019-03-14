package com.zwl.common.exception;

/**
 * 业务异常，用于统一处理
 *
 * @author 二师兄超级帅
 * @since 2018/11/22 5:18 PM
 */
public class BizException extends RuntimeException {

    private int code;
    private String msg;

    public BizException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BizException(int code, String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public BizException(ErrorEnum err) {
        super(err.getMsg());
        this.msg = err.getMsg();
        this.code = err.getCode();
    }

    public BizException(ErrorEnum err, Exception e) {
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
