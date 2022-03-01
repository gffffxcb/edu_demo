package com.mgh.sta.controller;


import com.mgh.commanUtils.MyResult;
import com.mgh.sta.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/sta/statistics-daily")
@CrossOrigin
@Api("数据统计接口管理")
@Slf4j
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService staService;

    @PostMapping("/{day}")
    public MyResult createStatisticsByDate(@PathVariable String day) {
        staService.createStatisticsByDay(day);
        return MyResult.ok();
    }
}

