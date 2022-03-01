package com.mgh.serviceBase.exception;

import com.mgh.commanUtils.ExceptionUtil;
import com.mgh.commanUtils.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MGH
 * @create 2022-0211 12:47 下午
 * 统一异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseBody //自定义异常处理
    public MyResult MyError(MyException e) {
        return MyResult.error().message(e.getMsg()).code(e.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MyResult error(Exception e) {
        //全局异常返回，若想根据不同异常类型返回不同内容，更改@ExceptionHandler()设定该异常类
        log.error(ExceptionUtil.getMessage(e)); //使用异常处理工具类打印信息
        e.printStackTrace();
        return MyResult.error();
    }

}
