package com.mgh.edu.client;

import com.mgh.commanUtils.MyResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author MGH
 * @create 2022-0222 4:54 下午
 */
@Component
public class VodFeignClientFallback implements VodClient {
    @Override
    public MyResult deleteVideoById(String videoId) {
        return MyResult.error().message("Feign删除失败");
    }

    @Override
    public MyResult deleteVideoBatch(List<String> videoIdList) {
        return MyResult.error().message("Feign批量删除失败");
    }
}
