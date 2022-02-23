package com.mgh.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.edu.entity.Teacher;
import com.mgh.edu.entity.vo.TeacherQuery;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-09
 */
public interface TeacherService extends IService<Teacher> {
    public List<Teacher> selectAll();
    public Integer deleteById(Long id);
    public Page getPage(Integer nowPage, Integer pageSize);
    public Page getTeacherQuery(Integer nowPage, Integer pageSize, TeacherQuery teacherQuery);
    public Integer addTeacher(Teacher teacher);
    public Integer updateTeacher(Teacher teacher);
}
