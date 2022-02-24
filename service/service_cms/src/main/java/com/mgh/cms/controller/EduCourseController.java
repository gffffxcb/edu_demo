package com.mgh.cms.controller;


import com.mgh.cms.entity.EduCourse;
import com.mgh.cms.entity.EduTeacher;
import com.mgh.cms.service.EduCourseService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取viewCount前8条讲师信息")
    @GetMapping
    public MyResult getEightCourse(){
        List<Map<String, Object>> courses=  courseService.getEightCourse();
        if (!courses.isEmpty()){
            return MyResult.ok().data("items",courses);
        }
        return MyResult.error();
    }

}

