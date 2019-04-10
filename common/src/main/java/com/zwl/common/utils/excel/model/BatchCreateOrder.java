package com.zwl.common.utils.excel.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 二师兄超级帅
 * @Title: BatchCreateOrder
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/4/315:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BatchCreateOrder implements java.io.Serializable {
    @Excel(name = "手机号", width = 25)
    private String phone;
    @Excel(name = "姓名", width = 25, orderNum = "1")
    private String name;
    @Excel(name = "商品名称", width = 25, orderNum = "2")
    private String goodsName;
    @Excel(name = "商品ID", width = 25, orderNum = "3")
    private Long gid;
    @Excel(name = "数量", width = 25, orderNum = "4")
    private Integer productQuantity;
    @Excel(name = "实付金额", width = 25, orderNum = "5")
    private Integer totalFee;
    @Excel(name = "状态", width = 25, orderNum = "6")
    private String remark;


}
