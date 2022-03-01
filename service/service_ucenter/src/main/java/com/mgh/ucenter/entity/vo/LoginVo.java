package com.mgh.ucenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author MGH
 * @create 2022-0224 2:21 下午
 */
@Data
@ApiModel(value = "登录对象", description = "登录对象")
public class LoginVo {
    @ApiModelProperty(value = "邮箱")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
}