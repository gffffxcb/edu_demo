package com.mgh.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.edu.entity.CourseDescription;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Repository("courseDescriptionMapper")
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {

}
