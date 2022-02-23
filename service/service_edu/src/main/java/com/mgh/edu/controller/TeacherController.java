package com.mgh.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.commanUtils.MyResult;
import com.mgh.edu.entity.Teacher;
import com.mgh.edu.entity.vo.TeacherQuery;
import com.mgh.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-09
 */
@Slf4j
@Api(value = "讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @ApiOperation("所有讲师名称/id")
    @GetMapping
    public MyResult getAll() {
        List<Teacher> teachers = teacherService.selectAll();
        return MyResult.ok().data("items", teachers);
    }

    @ApiOperation("逻辑删除ByID")
    @DeleteMapping("{id}")
    public MyResult deleteById(@PathVariable(value = "id", required = true) Long id) {
        Integer flag = teacherService.deleteById(id);
        if (flag != 1) {
            return MyResult.error();
        }
        return MyResult.ok();
    }

    @ApiOperation("获取分页信息")
    @GetMapping("/{nowPage}/{pageSize}")
    public MyResult getPage(@PathVariable(value = "nowPage") Integer nowPage, @PathVariable(value = "pageSize") Integer pageSize) {
        Page page = teacherService.getPage(nowPage, pageSize);
        return MyResult.ok().data("items", page.getRecords()).data("total", page.getTotal());
    }

    @ApiOperation("条件查询")
    @PostMapping("/{nowPage}/{pageSize}")
    public MyResult getQueryTeacher(@PathVariable(value = "nowPage") Integer nowPage, @PathVariable(value = "pageSize") Integer pageSize, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page query = teacherService.getTeacherQuery(nowPage, pageSize, teacherQuery);
        return MyResult.ok().data("items", query.getRecords()).data("total", query.getTotal());
    }

    @ApiOperation("添加成员")
    @PostMapping
    public MyResult addTeacher(@RequestBody Teacher teacher) {
        Integer flag = teacherService.addTeacher(teacher);
        log.info("post");
        if (flag == 1) {
            return MyResult.ok();
        }
        return MyResult.error();
    }

    @ApiOperation("查询成员ById")
    @GetMapping("/{id}")
    public MyResult getTeacherById(@PathVariable(value = "id", required = false) Long id) {
        Teacher teacher = teacherService.getById(id);
        return MyResult.ok().data("items",teacher);
    }

    @ApiOperation("修改成员")
    @PutMapping
    public MyResult updateTeacher(@RequestBody Teacher teacher) {
        log.info("put");
        Integer flag = teacherService.updateTeacher(teacher);
        if (flag == 1) {
            return MyResult.ok();
        }
        return MyResult.error();
    }

}

