package com.mgh.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.commanUtils.JwtUtil;
import com.mgh.commanUtils.MyResult;
import com.mgh.order.entity.TOrder;
import com.mgh.order.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-28
 */
@RestController("tOrderController")
@RequestMapping("/order/t-order")
@CrossOrigin
@Slf4j
@Api("支付订单管理")
public class TOrderController {
    @Autowired
    private TOrderService orderService;

    @PostMapping("/createOrder/{courseId}")
    public MyResult createOrder(@PathVariable(value = "courseId") String courseId, HttpServletRequest request) {
        String token = request.getHeader("token");
        HashMap<String, String> tokenInfo = JwtUtil.getIdByJwtToken(token);
        String orderNo = orderService.createOrder(courseId, tokenInfo.get("id"));
        return MyResult.ok().data("orderNo", orderNo);
    }

    @GetMapping("/getOrderInfo/{orderNo}")
    public MyResult getOrderInfo(@PathVariable(value = "orderNo") String orderNo) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        TOrder orderInfo = orderService.getOne(queryWrapper);
        if (orderInfo != null) {
            return MyResult.ok().data("items", orderInfo);
        }
        return MyResult.error();
    }

    @ApiOperation("根据用户ID和课程Id查询该用户是否购买")
    @GetMapping("/getPayInfoByMember/{memberId}/{courseId}")
    public MyResult getPayInfoByMember(@PathVariable(value = "memberId") String memberId, @PathVariable(value = "courseId") String courseId) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("course_id", courseId);
        TOrder order = orderService.getOne(queryWrapper);
        if (order!= null&&order.getStatus() == 1) {
            return MyResult.ok().data("payStatus", 1); //1已支付，0未支付
        }
        return MyResult.ok().data("payStatus", 0);
    }
}

