package com.mgh.cms.service.impl;

import com.mgh.cms.entity.EduVideo;
import com.mgh.cms.mapper.EduVideoMapper;
import com.mgh.cms.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@Service("eduVideoService")
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private EduVideoMapper videoMapper;
}
