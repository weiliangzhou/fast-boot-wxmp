package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.terran4j.commons.api2doc.annotations.ApiComment;
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


    @ApiComment(value = "主键", sample = "1")
    @TableId(value = "operation_log_id", type = IdType.AUTO)
    private Integer operationLogId;

    @ApiComment(value = "日志描述", sample = "1")
    private String logDescription;

    @ApiComment(value = "方法参数", sample = "1")
    private String actionArgs;

    @ApiComment(value = "用户主键", sample = "1")
    private String userNo;

    @ApiComment(value = "类名称", sample = "1")
    private String className;

    @ApiComment(value = "方法名称", sample = "1")
    private String methodName;

    private String ip;

    @ApiComment(value = "创建时间", sample = "1")
    private Long createTime;

    @ApiComment(value = "模块名称", sample = "1")
    private String modelName;

    @ApiComment(value = "操作", sample = "1")
    private String action;

    @ApiComment(value = "是否成功 1:成功 2异常", sample = "1")
    private Integer succeed;

    @ApiComment(value = "异常堆栈信息", sample = "1")
    private String message;


    @Override
    protected Serializable pkVal() {
        return this.operationLogId;
    }

}
