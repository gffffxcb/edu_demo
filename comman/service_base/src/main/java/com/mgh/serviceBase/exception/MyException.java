package com.mgh.serviceBase.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MGH
 * @create 2022-0216 10:26 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "自定义信息")
    private String msg;
}
