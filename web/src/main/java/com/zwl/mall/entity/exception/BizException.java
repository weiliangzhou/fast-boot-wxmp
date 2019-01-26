package com.zwl.mall.entity.exception;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
public class BizException extends RuntimeException {

    private Integer code;
    private String errmsg;
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BizException(Integer code, String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
        this.code = code;
    }

    public BizException(Integer code, String errmsg, Exception e) {
        super(errmsg, e);
        this.errmsg = errmsg;
        this.code = code;
    }

    public BizException(ErrorEnum err) {
        super(err.getErrmsg());
        this.errmsg = err.getErrmsg();
        this.code = err.getCode();
    }

    public BizException(ErrorEnum err, Exception e) {
        super(err.getErrmsg(), e);
        this.errmsg = err.getErrmsg();
        this.code = err.getCode();
    }


}
