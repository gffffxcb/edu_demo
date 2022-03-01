package com.mgh.vod.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.mgh.commanUtils.MyResult;
import com.mgh.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @author MGH
 * @create 2022-0221 1:26 下午
 */
@RestController("vodController")
@RequestMapping("/eduVod")
@CrossOrigin
@Slf4j
@Api("视频处理模块")
public class VodController {
    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频")
    @PostMapping("/uploadVideo") //上传视频
    public MyResult uploadAliVideo(MultipartFile file) {
        String title = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")); //视频的title名称，去掉后缀
        Map<String, Object> result = vodService.uploadAliVideo(file, title);
        if (result != null) {
            return MyResult.ok().data("videoId", result.get("videoId")).data("filename", title).data("videoSize", result.get("videoSize"))
                    .data("videoStatus", "Normal").data("videoDuration", result.get("videoDuration")).message("上传成功");
        }
        return MyResult.error().message("上传失败");
    }

    @GetMapping("/getPlayAuth/{videoId}") //获取视频播放地址以及播放凭证
    public MyResult getAliVideoPlayAuth(@PathVariable(value = "videoId")String videoId) {
       Map<String,String> videoMap= vodService.getAliVideoPlayAuth(videoId);
        return MyResult.ok().data("items", videoMap);
    }

    @DeleteMapping("/{videoId}") //删除云端视频
    public MyResult deleteVideoById(@PathVariable(name = "videoId") String videoId) {
        Boolean result = vodService.deleteVideoById(videoId);
        log.info("删除云端视频byId"+videoId);
        if (result){
            return MyResult.ok().message("删除成功");
        }
        return MyResult.error().message("视频删除失败");
    }
    @DeleteMapping("/deleteBatch") //批量删除云端视频
    public MyResult deleteVideoBatch(@RequestParam List<String> videoIdList) {
        Boolean result = vodService.deleteVideoBatch(videoIdList);
        log.info("删除云端视频byId"+videoIdList.toString());
        if (result){
            return MyResult.ok().message("删除成功");
        }
        return MyResult.error().message("视频删除失败");
    }

}
