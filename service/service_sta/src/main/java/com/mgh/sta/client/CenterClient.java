package com.mgh.sta.client;

import com.mgh.commanUtils.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MGH
 * @create 2022-0301 3:30 下午
 */
@Component
@FeignClient(value = "service-ucenter", fallback = CenterClientFallBack.class)
public interface CenterClient {
    @GetMapping("/api/ucenter/member/registerCount/{day}")
    public MyResult getRegisterCount(@PathVariable(value = "day") String day);
}
