package com.mgh.order.service;

import com.mgh.order.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-28
 */
public interface TPayLogService extends IService<TPayLog> {

    Map createNative(String orderNo);

    Map<String, String> queryOrderStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
