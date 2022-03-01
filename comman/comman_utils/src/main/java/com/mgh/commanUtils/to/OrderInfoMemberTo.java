package com.mgh.commanUtils.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author MGH
 * @create 2022-0224 2:22 下午
 */
@Data
@ApiModel(value = "订单用户信息", description = "订单用户信息")
public class OrderInfoMemberTo {
    @ApiModelProperty(value = "id")
    private String memberId;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "邮箱")
    private String mobile;
}