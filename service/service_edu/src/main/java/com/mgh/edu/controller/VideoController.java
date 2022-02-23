package com.mgh.edu.controller;


import com.mgh.commanUtils.MyResult;
import com.mgh.edu.client.VodClient;
import com.mgh.edu.entity.Video;
import com.mgh.edu.service.VideoService;
import com.mgh.serviceBase.exception.MyException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Api("课时管理")
@RestController("videoController")
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    @GetMapping("/{id}")
    public MyResult getVideoById(@PathVariable(name = "id") String id) {
        Video video = videoService.getById(id);
        if (video != null) {
            return MyResult.ok().data("items", video).message("查找成功");
        }
        return MyResult.error().message("查找失败，请刷新后尝试");

    }

    @PostMapping
    public MyResult addVideo(@RequestBody Video video) {
        String id = videoService.addVideo(video);
        if (id != null) {
            return MyResult.ok().data("items", id).message("添加成功");
        }
        return MyResult.error().message("添加失败，请刷新后尝试");

    }

    @PutMapping
    public MyResult updateVideo(@RequestBody Video video) {
        Boolean result = videoService.updateVideo(video);
        if (result) {
            return MyResult.ok().message("更新成功");
        }
        return MyResult.error().message("更新失败");
    }

    @DeleteMapping("/{videoId}") //nocas服务调用同步删除视频
    public MyResult deleteVideo(@PathVariable(name = "videoId") String id) {
        Video video = videoService.getById(id);
        if (!StringUtils.isEmpty(video.getVideoSourceId())){
            MyResult result = vodClient.deleteVideoById(video.getVideoSourceId()); //先删除云端视频
            if (!result.getSuccess()){
                throw new MyException(20001,"熔断器执行。。。。");
            }
        }
        Boolean result = videoService.deleteVideo(id); //删除视频信息
        if (result) {
            return MyResult.ok().message("删除成功");
        }
        return MyResult.error().message("删除失败");
    }
}

