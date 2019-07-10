package com.zwl.mall.controller.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zwl.mall.dao.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019/7/10 14:15
 * @Author: 二师兄超级帅
 * @Description: 提币记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountVo {
    private String total;
    private String balance;
    private IPage<UserAccount> userAccountIPage;
}
