package com.mgh.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.edu.entity.CourseDescription;
import com.mgh.edu.mapper.CourseDescriptionMapper;
import com.mgh.edu.service.CourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Service("courseDescriptionServiceImpl")
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

    @Autowired
    private CourseDescriptionMapper mapper;
}
