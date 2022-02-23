package com.mgh.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.edu.entity.Subject;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Repository("subjectMapper")
public interface SubjectMapper extends BaseMapper<Subject> {

}
