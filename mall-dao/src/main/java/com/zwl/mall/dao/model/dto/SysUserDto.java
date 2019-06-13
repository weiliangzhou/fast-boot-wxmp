package com.zwl.mall.dao.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ApiModelProperty(value = "标题")
    private String account;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private LocalDateTime regTime;
}
