package com.mgh.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author MGH
 * @create 2022-0301 9:09 上午
 */
public class OrderNoUtils {
    //订单号数据数
    public static String getOrderNo() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String orderNo = sdf.format(new Date()) + hashCodeV;
        return orderNo;
    }
}
