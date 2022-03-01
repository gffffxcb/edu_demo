package com.mgh.sta.service.impl;

import com.alibaba.nacos.common.utils.RandomUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.sta.client.CenterClient;
import com.mgh.sta.entity.StatisticsDaily;
import com.mgh.sta.mapper.StatisticsDailyMapper;
import com.mgh.sta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-03-01
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private CenterClient centerClient;
    @Autowired
    private StatisticsDailyMapper staMapper;

    @Override
    public void createStatisticsByDay(String day) {
        //1、先查询出是否有该天记录
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", day);
        StatisticsDaily statisticsDaily = staMapper.selectOne(queryWrapper);
        //2、查询最新统计信息
        Integer registerNum = (Integer) centerClient.getRegisterCount(day).getData().get("registerNum");
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO
        //3、添加统计信息\更新统计信息
        if (statisticsDaily != null) {
            statisticsDaily.setRegisterNum(registerNum);
            statisticsDaily.setLoginNum(loginNum);
            statisticsDaily.setVideoViewNum(videoViewNum);
            statisticsDaily.setCourseNum(courseNum);
            statisticsDaily.setDateCalculated(day);
            staMapper.updateById(statisticsDaily);
        } else {
            StatisticsDaily newDaily = new StatisticsDaily();
            newDaily.setRegisterNum(registerNum);
            newDaily.setLoginNum(loginNum);
            newDaily.setVideoViewNum(videoViewNum);
            newDaily.setCourseNum(courseNum);
            newDaily.setDateCalculated(day);
            staMapper.insert(newDaily);
        }
    }
}
