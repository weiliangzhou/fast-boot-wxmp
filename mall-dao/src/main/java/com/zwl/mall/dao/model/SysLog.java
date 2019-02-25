package com.zwl.mall.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 自定义模板
 *
 * </p>
 * 自定义属性注入abc=
 *
 * @author 二师兄超级帅
 * @since 2019-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog extends Model<SysLog> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private LocalDateTime createAt;

    @ApiComment(value = "功能描述", sample = "1")
    private String description;

    @ApiComment(value = "操作员", sample = "1")
    private String operator;

    private Integer version;

    @ApiComment(value = "模块", sample = "1")
    private String module;

    @ApiComment(value = "参数", sample = "1")
    private String content;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
