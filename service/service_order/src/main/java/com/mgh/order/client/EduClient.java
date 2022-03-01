package com.mgh.order.client;

import com.mgh.commanUtils.to.OrderInfoCourseTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MGH
 * @create 2022-0228 3:35 下午
 */
@Component
@FeignClient(value = "service-edu",fallback = EduClientFallBack.class)
public interface EduClient {
    @GetMapping("/edu/course/orderInfo/{id}")
    public OrderInfoCourseTo getCourseInfoToOrder(@PathVariable( value = "id") String id);
}
