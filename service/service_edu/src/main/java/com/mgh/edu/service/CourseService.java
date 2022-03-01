package com.mgh.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.commanUtils.to.OrderInfoCourseTo;
import com.mgh.edu.entity.Course;
import com.mgh.edu.entity.vo.CourseInfoVo;
import com.mgh.edu.entity.vo.CourseListVo;
import com.mgh.edu.entity.vo.CoursePublicInfoVo;
import com.mgh.edu.entity.vo.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
public interface CourseService extends IService<Course> {

   public String saveCourse(CourseInfoVo infoVo);

    CourseInfoVo getCourseInfo(String id);

    String updateCourse(CourseInfoVo infoVo);

    CoursePublicInfoVo getPublicInfo(String id);

    Boolean publicCourse(String id);

    Page<CourseListVo> getCourseQuery(Integer nowPage, Integer pageSize, CourseQuery courseQuery);

    Boolean deleteCourseById(String id);

    OrderInfoCourseTo getOrderInfoByCourse(String id);
}
