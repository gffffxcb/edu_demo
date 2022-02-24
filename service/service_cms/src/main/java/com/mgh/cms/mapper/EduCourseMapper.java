package com.mgh.cms.mapper;

import com.mgh.cms.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}
