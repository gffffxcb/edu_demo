package com.mgh.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.edu.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-09
 */
@Repository("teacherMapper")
public interface TeacherMapper extends BaseMapper<Teacher> {

}
