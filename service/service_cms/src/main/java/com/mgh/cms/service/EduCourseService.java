package com.mgh.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.cms.entity.vo.CourseInfoVo;
import com.mgh.cms.entity.vo.CoursePageQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
public interface EduCourseService extends IService<EduCourse> {

    List<Map<String, Object>> getEightCourse();

    Page<EduCourse> getCourseQueryPage(Integer nowPage, Integer pageSize, CoursePageQuery query);

    CourseInfoVo getCourseInfoById(String courseId);

    void addViewCount(String courseId);
}
