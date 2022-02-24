package com.mgh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.cms.entity.EduCourse;
import com.mgh.cms.mapper.EduCourseMapper;
import com.mgh.cms.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@Service("eduCourseServiceImpl")
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseMapper courseMapper;

    @Override
    @Cacheable(value = "course", key = "'getEightCourse'")
    public List<Map<String, Object>> getEightCourse() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "title", "price", "cover", "buy_count", "view_count");
        queryWrapper.eq("status", "Normal");
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        List<Map<String, Object>> courses = courseMapper.selectMaps(queryWrapper);
        return courses;
    }
}
