package com.mgh.order.client;

import com.mgh.commanUtils.to.OrderInfoCourseTo;
import com.mgh.commanUtils.to.OrderInfoMemberTo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MGH
 * @create 2022-0228 3:34 下午
 */
@Component
@FeignClient(value = "service-ucenter",fallback = CenterClientFallBack.class)
public interface CenterClient {

    @GetMapping("/api/ucenter/member/orderInfo/{id}")
    public OrderInfoMemberTo getOrderInfoByMember(@PathVariable(value = "id") String id);
}
