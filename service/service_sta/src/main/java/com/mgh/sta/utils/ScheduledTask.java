package com.mgh.sta.utils;

import com.mgh.sta.service.StatisticsDailyService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author MGH
 * @create 2022-0301 4:08 下午
 * 定时执行工具类
 */
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;

    /**
     * 每五秒执行一次
     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void task1() {
//        System.out.println("*********++++++++++++*****执行了");
//    }

    /**
     * 每天凌晨2点执行定时
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void task2() {
        //获取上一天的日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String day = df.format(DateUtils.addDays(new Date(), -1));
        //定时每天凌晨2点执行执行
        staService.createStatisticsByDay(day);
    }
}
