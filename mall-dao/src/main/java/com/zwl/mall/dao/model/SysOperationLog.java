package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 自定义模板
 * 操作日志表
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_operation_log")
public class SysOperationLog extends Model<SysOperationLog> {


    @TableId(value = "operation_log_id", type = IdType.AUTO)
    private Integer operationLogId;

    private String logDescription;

    private String actionArgs;

    private String userNo;

    private String className;

    private String methodName;

    private String ip;

    private Long createTime;

    private String modelName;

    private String action;

    private Integer succeed;

    private String message;


    @Override
    protected Serializable pkVal() {
        return this.operationLogId;
    }

}
