package com.mgh.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.cms.entity.EduCourse;
import com.mgh.cms.entity.EduTeacher;
import com.mgh.cms.entity.vo.CourseInfoVo;
import com.mgh.cms.entity.vo.CoursePageQuery;
import com.mgh.cms.service.EduCourseService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@RestController("eduCourseController")
@RequestMapping("/cms/edu-course")
@CrossOrigin
@Slf4j
@Api("课程前端接口")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation("获取viewCount前8条课程信息")
    @GetMapping
    public MyResult getEightCourse() {
        List<Map<String, Object>> courses = courseService.getEightCourse();
        if (!courses.isEmpty()) {
            return MyResult.ok().data("items", courses);
        }
        return MyResult.error();
    }

    @ApiOperation("根据条件获取课程")
    @PostMapping("/getPageCourse/{nowPage}/{pageSize}")
    public MyResult getCourseQueryPage(@PathVariable(value = "nowPage") Integer nowPage, @PathVariable(value = "pageSize") Integer pageSize, @RequestBody CoursePageQuery query) {
        Page<EduCourse> page = courseService.getCourseQueryPage(nowPage, pageSize, query);
        return MyResult.ok().data("items", page.getRecords()).data("total", page.getTotal());
    }

    @ApiOperation("根据ID获取该课程详细信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public MyResult getCourseInfoById(@PathVariable(value = "courseId") String courseId) {
        CourseInfoVo courseInfo = courseService.getCourseInfoById(courseId);
        if (courseInfo != null) {
            return MyResult.ok().data("items", courseInfo);
        }
        return MyResult.error();
    }

    @ApiOperation("根据ID获取该课程详细信息")
    @PutMapping("/addViewCount/{courseId}")
    public void addViewCount(@PathVariable(value = "courseId") String courseId) {
        courseService.addViewCount(courseId);
    }
}

