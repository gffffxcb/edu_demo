package com.mgh.cms.controller;


import com.mgh.cms.entity.EduVideo;
import com.mgh.cms.mapper.EduVideoMapper;
import com.mgh.cms.service.EduVideoService;
import com.mgh.commanUtils.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/cms/edu-video")
@Slf4j
@CrossOrigin
@Api("前台小节视频接口")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @ApiOperation("获取视频本地信息")
    @GetMapping("/{videoId}")
    public MyResult getVideoInfoById(@PathVariable(value = "videoId") String videoId) {
        EduVideo video = videoService.getById(videoId);
        if (video != null) {
            return MyResult.ok().data("items", video);
        }
        return MyResult.error();
    }
}

