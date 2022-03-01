package com.mgh.sta.mapper;

import com.mgh.sta.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-03-01
 */
@Repository("statisticsDailyMapper")
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {

}
