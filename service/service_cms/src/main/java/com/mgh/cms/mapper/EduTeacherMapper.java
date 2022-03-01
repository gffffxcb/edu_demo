package com.mgh.cms.mapper;

import com.mgh.cms.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.cms.entity.vo.TeacherInfoVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@Repository("eduTeacherMapper")
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    TeacherInfoVo getTeacherInfoById(String teacherId);
}
