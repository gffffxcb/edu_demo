package com.mgh.edu.client;

import com.mgh.commanUtils.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MGH
 * @create 2022-0228 10:35 上午
 */
@Component("centerClient")
@FeignClient(name = "service-ucenter", fallback = CenterClientFallBack.class,configuration = FeignConfig.class)
public interface CenterClient {
    @GetMapping("/api/ucenter/member/tokenInfo")
    public MyResult getInfoByToken();
}
