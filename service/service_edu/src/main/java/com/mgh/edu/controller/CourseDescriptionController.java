package com.mgh.edu.controller;


import com.mgh.edu.service.CourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程简介 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@RestController("courseDescriptionController")
@RequestMapping("/edu/course-description")
@CrossOrigin
public class CourseDescriptionController {

    @Autowired
    private CourseDescriptionService courseDescriptionService;
}

