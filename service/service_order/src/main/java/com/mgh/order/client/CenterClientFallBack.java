package com.mgh.order.client;

import com.mgh.commanUtils.to.OrderInfoCourseTo;
import com.mgh.commanUtils.to.OrderInfoMemberTo;
import org.springframework.stereotype.Component;

/**
 * @author MGH
 * @create 2022-0228 3:38 下午
 */
@Component
public class CenterClientFallBack implements CenterClient{

    @Override
    public OrderInfoMemberTo getOrderInfoByMember(String id) {
        return null;
    }
}
