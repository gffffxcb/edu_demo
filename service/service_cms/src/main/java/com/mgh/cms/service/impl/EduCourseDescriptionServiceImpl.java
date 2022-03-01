package com.mgh.cms.service.impl;

import com.mgh.cms.entity.EduCourseDescription;
import com.mgh.cms.mapper.EduCourseDescriptionMapper;
import com.mgh.cms.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@Service("eduCourseDescriptionService")
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

}
