package com.zwl.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;
}
