package com.mgh.order.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.mgh.commanUtils.MyResult;
import com.mgh.order.service.TPayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-28
 */
@RestController("tPayLogController")
@RequestMapping("/order/pay-log")
@CrossOrigin
@Slf4j
@Api("付款交易日志")
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;

    @GetMapping("/createNative/{orderNo}")
    public MyResult createNative(@PathVariable(value = "orderNo") String orderNo) {
        Map map = payLogService.createNative(orderNo);
        return MyResult.ok().data("items", map);
    }

    @GetMapping("/queryStatus/{orderNo}")
    public MyResult queryOrderStatus(@PathVariable(value = "orderNo") String orderNo) {
        //查询支付状态
        Map<String, String> map = payLogService.queryOrderStatus(orderNo);
        log.info(map.toString());
        if (map == null) {
            return MyResult.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            //查询支付状态完成则更新Order表及payLog表中插入数据
            payLogService.updateOrderStatus(map);
            return MyResult.ok().data("trade_state","SUCCESS").message("支付完成");
        }
        return MyResult.ok().data("trade_state","NOTPAY"); //正在支付中
    }

}

