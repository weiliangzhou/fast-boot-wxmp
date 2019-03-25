package com.zwl.mall.exception;

/**
 * 参数异常
 *
 * @author 二师兄
 * @since 2018-05-06
 */
public class ParamJsonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ParamJsonException() {
    }

    public ParamJsonException(String message) {
        super(message);
        this.message = message;
    }


}
