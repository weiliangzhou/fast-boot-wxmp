package com.zwl.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("statistics_by_day")
public class StatisticsByDay extends Model<StatisticsByDay> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiComment(value = "发卡数量", sample = "1")
    private Integer sendCardNum;

    @ApiComment(value = "开卡数量", sample = "1")
    private Integer activateCardNum;

    @ApiComment(value = "1元销售量", sample = "1")
    private Integer saleNum;

    @ApiComment(value = "新增发卡数量", sample = "1")
    private Integer newSendCardNum;

    @ApiComment(value = "新增开卡数量", sample = "1")
    private Integer newActivateCardNum;

    @ApiComment(value = "新增vip数量", sample = "1")
    private Integer newVipNum;

    @ApiComment(value = "新增创业教练", sample = "1")
    private Integer newCyjlNum;

    @ApiComment(value = "新增院长", sample = "1")
    private Integer newYzNum;

    @ApiComment(value = "新增校长", sample = "1")
    private Integer newXzNum;

    @ApiComment(value = "新增小镇", sample = "1")
    private Integer newJdNum;

    @ApiComment(value = "创业教练", sample = "1")
    private Integer cyjlNum;

    @ApiComment(value = "院长", sample = "1")
    private Integer yzNum;

    @ApiComment(value = "校长", sample = "1")
    private Integer xzNum;

    @ApiComment(value = "小镇", sample = "1")
    private Integer jdNum;

    @ApiComment(value = "vip", sample = "1")
    private Integer vipNum;

    @ApiComment(value = "订单数", sample = "1")
    private Integer totalOrderNum;

    @ApiComment(value = "订单总金额", sample = "1")
    private Integer totalOrderAmount;

    @ApiComment(value = "新增预绑定用户", sample = "1")
    private Integer preBindingNum;

    @ApiComment(value = "新增注册用户数", sample = "1")
    private Integer registerNum;

    @ApiComment(value = "记录时间", sample = "1")
    private LocalDate recordTime;

    @ApiComment(value = "创建时间", sample = "1")
    private LocalDateTime createAt;

    @ApiComment(value = "更新时间", sample = "1")
    private LocalDateTime updateAt;

    @ApiComment(value = "逻辑删除位", sample = "1")
    private Boolean deleted;

    @ApiComment(value = "新增订单数", sample = "1")
    private Integer newTotalOrderNum;

    @ApiComment(value = "新增订单总金额", sample = "1")
    private Integer newTotalOrderAmount;

    @ApiComment(value = "新增预绑定用户", sample = "1")
    private Integer newPreBindingNum;

    @ApiComment(value = "新增注册用户", sample = "1")
    private Integer newRegisterNum;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
