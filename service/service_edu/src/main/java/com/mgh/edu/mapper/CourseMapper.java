package com.mgh.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.commanUtils.to.OrderInfoCourseTo;
import com.mgh.edu.entity.Course;
import com.mgh.edu.entity.vo.CourseInfoVo;
import com.mgh.edu.entity.vo.CourseListVo;
import com.mgh.edu.entity.vo.CoursePublicInfoVo;
import com.mgh.edu.entity.vo.CourseQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Repository("courseMapper")
public interface CourseMapper extends BaseMapper<Course> {

    CourseInfoVo getCourseInfo(String id);

    CoursePublicInfoVo getPublicInfo(String id);

    Integer updateStatusNormal(String id);

    Page<CourseListVo> getPageQuery(Page<CourseListVo> page, @Param("query") CourseQuery query);

    OrderInfoCourseTo getOrderInfoByCourse(String id);
}
