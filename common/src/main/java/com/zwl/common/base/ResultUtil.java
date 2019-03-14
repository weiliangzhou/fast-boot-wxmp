package com.zwl.common.base;

import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import com.zwl.common.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
@Slf4j
public class ResultUtil {

    public static final int SUCCESS_NO = 0;
    public static final String SUCCESS_MSG = "success";

    public static <T> Result<T> ok(T data) {
        return new Result<T>(SUCCESS_NO, SUCCESS_MSG, data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<T>(code, msg, null);
    }

    public static Result fail(Exception e) {
        if (BizException.class.isInstance(e)) {
            BizException be = (BizException) e;
            return ResultUtil.fail(be.getCode(), be.getMsg());
        } else if (MissingServletRequestParameterException.class.isInstance(e)) {
            SysException be = new SysException(com.zwl.common.exception.ErrorEnum.ARGUMENT_ERROR, e);
            return ResultUtil.fail(be);
        } else {
            SysException be = new SysException(ErrorEnum.UNKNOWN_ERROR, e);
            return ResultUtil.fail(be);
        }
    }
}
