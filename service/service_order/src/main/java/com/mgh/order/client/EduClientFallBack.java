package com.mgh.order.client;

import com.mgh.commanUtils.to.OrderInfoCourseTo;
import org.springframework.stereotype.Component;

/**
 * @author MGH
 * @create 2022-0228 3:38 下午
 */
@Component
public class EduClientFallBack implements EduClient{
    @Override
    public OrderInfoCourseTo getCourseInfoToOrder(String id) {
        return null;
    }
}
