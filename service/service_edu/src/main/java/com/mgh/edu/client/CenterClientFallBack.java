package com.mgh.edu.client;

import com.mgh.commanUtils.MyResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MGH
 * @create 2022-0228 10:37 上午
 */
@Component
public class CenterClientFallBack implements CenterClient{

    @Override
    public MyResult getInfoByToken() {
        return MyResult.error().message("FallBack执行，getInfoByToken返回错误！");
    }
}
