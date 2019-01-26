package com.zwl.mall.entity;

import com.zwl.mall.entity.exception.BizException;
import com.zwl.mall.entity.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * 结果封装
 *
 * @author paul <wagzhi@gmail.com>
 * @since 2018/11/15 5:09 PM
 */
@Slf4j
public class ResultUtil {

    public static final Integer SUCCESS_NO = 0;
    public static final Integer UNKNOW_ERR = 10000;
    public static final String SUCCESS_MSG = "success";

    public static <T> Result<T> ok(T data) {
        return new Result<T>(SUCCESS_NO, SUCCESS_MSG, data);
    }

    public static <T> Result<T> fail(Integer errno, String errmsg) {
        return new Result<T>(errno, errmsg, null);
    }

    public static Result fail(Exception e) {
        if (BizException.class.isInstance(e)) {
            BizException be = (BizException) e;
            return ResultUtil.fail(be.getCode(), be.getErrmsg());
        } else if (MissingServletRequestParameterException.class.isInstance(e)) {
            BizException be = new BizException(ErrorEnum.ArgumentError, e);
            return ResultUtil.fail(be);
        } else {
            BizException be = new BizException(ErrorEnum.UnknownError, e);
            return ResultUtil.fail(be);
        }
    }
}
