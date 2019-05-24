package com.zwl.mall.dao.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 二师兄
 * @Title: SysUserDto
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/5/2417:27
 */
@Data
public class SysUserDto {
    @ApiComment(value = "帐号", sample = "1")
    @ApiModelProperty(value = "标题")
    private String account;
    @JsonIgnore
    @ApiComment(value = "昵称", sample = "1")
    private String username;
    @JsonIgnore
    @ApiComment(value = "注册时间", sample = "1")
    private LocalDateTime regTime;
}
