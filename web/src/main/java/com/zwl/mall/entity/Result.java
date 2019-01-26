package com.zwl.mall.entity;

/**
 * @author paul <wagzhi@gmail.com>
 * @since 2018/11/15 5:09 PM
 */
public class Result<T> {

    private Integer errno;
    private String errmsg;
    private T data;

    public Result(Integer errno, String errmsg, T data){
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }
    public Result(Integer errno, String errmsg){
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
