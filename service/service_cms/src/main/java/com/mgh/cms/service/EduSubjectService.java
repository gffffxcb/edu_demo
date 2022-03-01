package com.mgh.cms.service;

import com.mgh.cms.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.cms.entity.vo.OneSubject;

import java.util.ArrayList;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    ArrayList<OneSubject> getAllOneSubject();
}
