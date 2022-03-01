package com.mgh.cms.controller;


import com.mgh.cms.entity.vo.ChapterAndVideoVo;
import com.mgh.cms.service.EduChapterService;
import com.mgh.cms.service.EduVideoService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@RestController("eduChapterController")
@RequestMapping("/cms/edu-chapter")
@CrossOrigin
@Slf4j
@Api("前端章节信息接口")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @ApiOperation("获取章节小节信息进行展示")
    @GetMapping("/getChapterVideo/{courseId}")
    public MyResult getChapterAndVideoInfo(@PathVariable(value = "courseId") String courseId) {
      List<ChapterAndVideoVo> infoList=  chapterService.getChapterAndVideoInfo(courseId);
      if(infoList.size()>0){
          return MyResult.ok().data("items",infoList);
      }
        return MyResult.error();
    }
}

