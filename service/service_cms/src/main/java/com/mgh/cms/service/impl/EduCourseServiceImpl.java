package com.mgh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.EduCourse;
import com.mgh.cms.entity.vo.CourseInfoVo;
import com.mgh.cms.entity.vo.CoursePageQuery;
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

    @Override
    public Page<EduCourse> getCourseQueryPage(Integer nowPage, Integer pageSize, CoursePageQuery query) {
        Page<EduCourse> page = new Page<>(nowPage, pageSize);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "Normal");
        if (!query.getTwoSubjectId().isEmpty()) {
            queryWrapper.eq("subject_id", query.getTwoSubjectId());
        }
        if (query.getViewCountSort() == 1) {
            queryWrapper.orderByDesc("view_count");
        }
        if (query.getPriceSort() == 1) {
            queryWrapper.orderByAsc("price");
        }
        if (query.getGmtModifiedSort() == 1) {
            queryWrapper.orderByDesc("gmt_modified");
        }
        Page<EduCourse> eduCoursePage = courseMapper.selectPage(page, queryWrapper);
        return eduCoursePage;
    }

    @Override
    public CourseInfoVo getCourseInfoById(String courseId) {
        CourseInfoVo info = courseMapper.getCourseInfoById(courseId);
        return info;
    }

    @Override
    public void addViewCount(String courseId) {
        courseMapper.addViewCount(courseId);
    }
}
