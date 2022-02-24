package com.mgh.cms.controller;


import com.mgh.cms.entity.EduTeacher;
import com.mgh.cms.service.EduTeacherService;
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
 * 讲师 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-23
 */
@RestController("eduTeacherController")
@RequestMapping("/cms/edu-teacher")
@CrossOrigin
@Slf4j
@Api("讲师前端接口")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation("获取sort前四条讲师信息")
    @GetMapping
    public MyResult getFourTeacher(){
        List<Map<String, Object>> teachers=  teacherService.getFourTeacher();
      if (!teachers.isEmpty()){
          return MyResult.ok().data("items",teachers);
      }
        return MyResult.error();
    }
}

