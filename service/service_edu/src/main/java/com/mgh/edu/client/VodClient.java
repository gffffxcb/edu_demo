package com.mgh.edu.client;

import com.mgh.commanUtils.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author MGH
 * @create 2022-0222 11:36 上午
 */
@FeignClient(name = "service-vod",fallback = VodFeignClientFallback.class) //注册中心的服务名称
@Component("vodClient")
public interface VodClient {
    @DeleteMapping("/eduVod/{videoId}") //添加删除云端视频接口
    public MyResult deleteVideoById(@PathVariable(name = "videoId") String videoId);
    @DeleteMapping("/eduVod/deleteBatch") //批量删除云端视频
    public MyResult deleteVideoBatch(@RequestParam List<String> videoIdList);
}
