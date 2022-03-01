package com.mgh.cms.controller;


import com.mgh.cms.entity.vo.OneSubject;
import com.mgh.cms.service.EduSubjectService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-26
 */
@RestController("eduSubjectController")
@RequestMapping("/cms/edu-subject")
@CrossOrigin
@Slf4j
@Api("科目分类前端接口")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation("获取所有课程一级分类")
    @GetMapping("/getAllSubject")
    public MyResult getAllOneSubject() {
        ArrayList<OneSubject> subjectList = subjectService.getAllOneSubject();
        if (subjectList!=null) {
            return MyResult.ok().data("items", subjectList);
        }
        return MyResult.error();
    }
}

