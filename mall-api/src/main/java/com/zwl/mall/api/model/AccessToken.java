package com.zwl.mall.api.model;

import com.zwl.mall.dao.model.UserBase;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 二师兄
 * @Title: AccessToken
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/6/1316:44
 */
@Data
@AllArgsConstructor
public class AccessToken {
    private String token;
    private UserBase userBase;
}
