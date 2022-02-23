package com.mgh.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.edu.entity.Teacher;
import com.mgh.edu.entity.vo.TeacherQuery;
import com.mgh.edu.mapper.TeacherMapper;
import com.mgh.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-09
 */
@Service("teacherService")
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectAll() {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.select("id","name");
        return teacherMapper.selectList(wrapper);
    }

    @Override
    public Integer deleteById(Long id) {
        int i = teacherMapper.deleteById(id);
        return i;
    }

    @Override
    public Page getPage(Integer nowPage, Integer pageSize) {
        Page page = new Page<Teacher>(nowPage, pageSize);
        Page selectPage = teacherMapper.selectPage(page, null);
        return selectPage;
    }

    @Override
    public Page getTeacherQuery(Integer nowPage, Integer pageSize, TeacherQuery teacherQuery) {
        Page page = new Page<Teacher>(nowPage, pageSize);
        QueryWrapper<Teacher> wrapper = Wrappers.query();
        if (!StringUtils.isEmpty(teacherQuery.getName())) {
            wrapper.like("name", teacherQuery.getName());
        }
        if (!StringUtils.isEmpty(teacherQuery.getLevel())) {
            wrapper.eq("level", teacherQuery.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQuery.getBegin())) {
            wrapper.ge("gmt_create", teacherQuery.getBegin());
        }
        if (!StringUtils.isEmpty(teacherQuery.getEnd())) {
            wrapper.le("gmt_create", teacherQuery.getEnd());
        }
        Page selectPage = teacherMapper.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        int insert = teacherMapper.insert(teacher);
        return insert;
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        int flag = teacherMapper.updateById(teacher);
        return flag;
    }
}
