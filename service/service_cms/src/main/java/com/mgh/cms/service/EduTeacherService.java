package com.mgh.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.cms.entity.vo.TeacherInfoVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<Map<String, Object>> getFourTeacher();

    Page<EduTeacher> getTeacherPage(Integer nowPage, Integer pageSize);

    TeacherInfoVo getTeacherInfoById(String teacherId);
}
