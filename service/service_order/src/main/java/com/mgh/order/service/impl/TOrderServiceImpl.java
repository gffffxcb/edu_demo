package com.mgh.order.service.impl;

import com.mgh.commanUtils.to.OrderInfoCourseTo;
import com.mgh.commanUtils.to.OrderInfoMemberTo;
import com.mgh.order.client.CenterClient;
import com.mgh.order.client.EduClient;
import com.mgh.order.entity.TOrder;
import com.mgh.order.mapper.TOrderMapper;
import com.mgh.order.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.order.utils.OrderNoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-28
 * <p>
 * 点击付款时先生成订单信息，然后付款后才会执行付款日志并更新订单表
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private CenterClient centerClient;
    @Autowired
    private EduClient eduClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        TOrder order = new TOrder();
        OrderInfoMemberTo memberInfo = centerClient.getOrderInfoByMember(memberId);
        OrderInfoCourseTo courseInfo = eduClient.getCourseInfoToOrder(courseId);
        BeanUtils.copyProperties(memberInfo,order);
        BeanUtils.copyProperties(courseInfo,order);
        //订单号数据数
        String orderNo = OrderNoUtils.getOrderNo();
        order.setOrderNo(orderNo);
        order.setPayType(1);
        order.setStatus(0);
        int result = orderMapper.insert(order);
        if (result==1){
            return orderNo;
        }
        return null;
    }
}
