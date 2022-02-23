package com.mgh.edu.controller;


import com.mgh.commanUtils.MyResult;
import com.mgh.edu.entity.vo.OneSubject;
import com.mgh.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@RestController("subjectController")
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping //添加分类，excel表导入
    public MyResult addSubject(MultipartFile file){
        subjectService.saveSubject(file,subjectService);
        return MyResult.ok();
    }

    @GetMapping //获取课程分类
    public MyResult getSubject(){
        List<OneSubject> list = subjectService.getAll();
        return MyResult.ok().data("items",list);
    }
}

