package com.zwl.mall.system.config;

import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.common.exception.BizException;
import com.zwl.common.exception.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Date: 2019/7/5 14:32
 * @Author: 二师兄超级帅
 * @Description: 全局异常拦截
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.fail(ErrorEnum.SYS_ERROR.getCode(), ErrorEnum.SYS_ERROR.getMsg());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.fail(ErrorEnum.ARGUMENT_ERROR.getCode(), ErrorEnum.ARGUMENT_ERROR.getMsg());
    }

    @ExceptionHandler(value = BizException.class)
    public Result errorHandler(BizException e) {
        log.info(e.getMsg(), e);
        return ResultUtil.fail(e.getCode(), e.getMsg());
    }

}
