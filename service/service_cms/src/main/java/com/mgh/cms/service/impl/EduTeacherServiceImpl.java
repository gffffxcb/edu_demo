package com.mgh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.cms.entity.EduTeacher;
import com.mgh.cms.mapper.EduTeacherMapper;
import com.mgh.cms.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@Service("eduTeacherServiceImpl")
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper teacherMapper;

    @Override
    @Cacheable(value = "teacher", key = "'getFourTeacher'")
    public List<Map<String, Object>> getFourTeacher() {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "intro", "career", "avatar");
        queryWrapper.orderByAsc("sort");
        queryWrapper.last("limit 4");
        List<Map<String, Object>> teachers = teacherMapper.selectMaps(queryWrapper);
        return teachers;
    }
}
