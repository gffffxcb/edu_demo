package com.mgh.cms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.cms.entity.vo.CourseInfoVo;
import com.mgh.cms.entity.vo.CoursePageQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@Repository("eduCourseMapper")
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CourseInfoVo getCourseInfoById(String courseId);

    void addViewCount(String courseId);
}
