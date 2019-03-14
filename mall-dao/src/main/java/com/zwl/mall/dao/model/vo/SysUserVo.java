package com.zwl.mall.dao.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zwl.mall.dao.model.SysUser;

import java.util.Date;

/**
 * @author 二师兄超级帅
 * @Title: SysUserVo
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/3/1417:34
 */
public class SysUserVo extends SysUser {
    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
