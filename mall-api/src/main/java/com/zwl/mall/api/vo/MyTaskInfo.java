package com.zwl.mall.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Date: 2019/7/3 11:54
 * @Author: 二师兄超级帅
 * @Description:
 */

@Data
@AllArgsConstructor
public class MyTaskInfo {
    private int type;
    private String desc;
    private boolean complete;
    private String btnName;
}
