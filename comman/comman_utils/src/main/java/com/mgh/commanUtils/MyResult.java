package com.mgh.commanUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MGH
 * @create 2022-0210 2:09 下午
 */
@Data
@ToString
public class MyResult {
    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回数据")
    private Map<String, Object> data =new HashMap < String, Object>(); //设置一个对象防止在data方法put数据时异常

    private MyResult() {
        //私有方法，该类不能随意构建
    }

    public static MyResult ok() {
        MyResult result = new MyResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    public static MyResult error() {
        MyResult result = new MyResult();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }

    public MyResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public MyResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public MyResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public MyResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
