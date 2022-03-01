package com.mgh.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.commanUtils.to.OrderInfoCourseTo;
import com.mgh.edu.client.VodClient;
import com.mgh.edu.entity.Chapter;
import com.mgh.edu.entity.Course;
import com.mgh.edu.entity.CourseDescription;
import com.mgh.edu.entity.Video;
import com.mgh.edu.entity.vo.CourseInfoVo;
import com.mgh.edu.entity.vo.CourseListVo;
import com.mgh.edu.entity.vo.CoursePublicInfoVo;
import com.mgh.edu.entity.vo.CourseQuery;
import com.mgh.edu.mapper.ChapterMapper;
import com.mgh.edu.mapper.CourseDescriptionMapper;
import com.mgh.edu.mapper.CourseMapper;
import com.mgh.edu.mapper.VideoMapper;
import com.mgh.edu.service.CourseService;
import com.mgh.serviceBase.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Service("courseServiceImpl")
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VodClient vodClient;

    @Override
    public String saveCourse(CourseInfoVo infoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(infoVo, course);
        int flag1 = courseMapper.insert(course);
        if (flag1 != 1) {
            throw new MyException(20001, "课程信息添加失败");
        }
        String courseId = course.getId(); //获取新建课程的id
        CourseDescription description = new CourseDescription();
        BeanUtils.copyProperties(infoVo, description);
        description.setId(courseId);
        int flag2 = courseDescriptionMapper.insert(description);
        if (flag2 != 1) {
            throw new MyException(20001, "课程简介信息添加失败");
        }
        return courseId;
    }

    @Override
    public CourseInfoVo getCourseInfo(String id) {
        CourseInfoVo result = courseMapper.getCourseInfo(id);
        return result;
    }

    @Override
    public String updateCourse(CourseInfoVo infoVo) {
        Course course = new Course();
        CourseDescription description = new CourseDescription();
        BeanUtils.copyProperties(infoVo, course);
        BeanUtils.copyProperties(infoVo, description);
        int result1 = courseMapper.updateById(course);
        int result2 = courseDescriptionMapper.updateById(description);
        if (result1 != 0 && result2 != 0) {
            return infoVo.getId();
        }
        return null;
    }

    @Override
    public CoursePublicInfoVo getPublicInfo(String id) {
        CoursePublicInfoVo info = courseMapper.getPublicInfo(id);
        return info;
    }

    @Override
    public Boolean publicCourse(String id) {
        Integer status = courseMapper.updateStatusNormal(id);
        if (status >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public Page<CourseListVo> getCourseQuery(Integer nowPage, Integer pageSize, CourseQuery courseQuery) {
        Page<CourseListVo> page = new Page<CourseListVo>(nowPage, pageSize);
        return courseMapper.getPageQuery(page, courseQuery);
    }

    @Override
    public Boolean deleteCourseById(String id) {
        QueryWrapper<Video> query1 = new QueryWrapper<>();
        query1.eq("course_id", id);
        int re1 = videoMapper.delete(query1);
        int re2 = courseDescriptionMapper.deleteById(id);
        QueryWrapper<Chapter> query2 = new QueryWrapper<>();
        query2.eq("course_id", id);
        int re3 = chapterMapper.delete(query2);
        int re4 = courseMapper.deleteById(id);
        if (re4 == 1) {
            return true;
        }
        return false;
    }

    @Override
    public OrderInfoCourseTo getOrderInfoByCourse(String id) {
        OrderInfoCourseTo orderInfoByCourse = courseMapper.getOrderInfoByCourse(id);
        return orderInfoByCourse;
    }
}