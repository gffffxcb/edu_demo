package com.mgh.edu.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.commanUtils.MyResult;
import com.mgh.edu.entity.vo.CourseInfoVo;
import com.mgh.edu.entity.vo.CourseListVo;
import com.mgh.edu.entity.vo.CoursePublicInfoVo;
import com.mgh.edu.entity.vo.CourseQuery;
import com.mgh.edu.service.CourseService;
import com.mgh.edu.service.VideoService;
import com.mgh.serviceBase.exception.MyException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@RestController("courseController")
@RequestMapping("/edu/course")
@CrossOrigin
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;

    @PostMapping
    public MyResult saveCourse(@RequestBody CourseInfoVo infoVo) {
        String courseId = courseService.saveCourse(infoVo);
        return MyResult.ok().data("courseId", courseId);
    }

    @GetMapping("/{id}") //添加课程信息修改时回显
    public MyResult getCourseInfo(@PathVariable(required = true, name = "id") String id) {
        CourseInfoVo result = courseService.getCourseInfo(id);
        return MyResult.ok().data("items", result);
    }

    @PutMapping
    public MyResult updateCourse(@RequestBody CourseInfoVo infoVo) {
        String courseId = courseService.updateCourse(infoVo);
        if (courseId != null) {
            return MyResult.ok().data("courseId", courseId);
        }
        return MyResult.error().message("更新失败");
    }

    @GetMapping("/publicInfo/{id}") //发布课程信息时信息核对
    public MyResult getPublicInfo(@PathVariable(name = "id") String id) {
        CoursePublicInfoVo result = courseService.getPublicInfo(id);
        if (result != null) {
            return MyResult.ok().data("items", result).message("查询成功");
        }
        return MyResult.error().message("查询失败，请重新尝试");
    }

    @PutMapping("/publicInfo/{id}") //发布课程信息
    public MyResult publicCourse(@PathVariable(name = "id") String id) {
        Boolean result = courseService.publicCourse(id);
        if (result) {
            return MyResult.ok().message("课程信息发布成功");
        }
        return MyResult.error().message("发布失败，请重新尝试");
    }
    @SentinelResource(value = "courseListInfo") //熔断机制处理
    @PostMapping("/courseListInfo/{nowPage}/{pageSize}") //获取course-list页面信息
    public MyResult getPageCourse(@PathVariable(value = "nowPage") Integer nowPage, @PathVariable(value = "pageSize") Integer pageSize, @RequestBody(required = false) CourseQuery courseQuery) {
        Page<CourseListVo> page = courseService.getCourseQuery(nowPage, pageSize, courseQuery);
        if (page != null) {
            return MyResult.ok().data("items", page.getRecords()).data("total", page.getTotal());
        }
        return MyResult.error().message("查询失败");
    }

    @ApiOperation("删除课程信息，包括简介，小节信息,云端视频")
    @DeleteMapping("/{id}")
    public MyResult deleteCourseById(@PathVariable(name = "id") String id) {
        Boolean vFlag = videoService.deleteVideoByCourseId(id);
        if (!vFlag) {
            throw new MyException(20001,"熔断器执行。。。。");
        }
        Boolean flag = courseService.deleteCourseById(id);
        if (flag) {
            return MyResult.ok().message("删除成功");
        }
        return MyResult.error().message("删除失败");
    }


}

