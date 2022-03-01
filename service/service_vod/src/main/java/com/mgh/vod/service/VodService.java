package com.mgh.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MGH
 * @create 2022-0221 1:26 下午
 */
public interface VodService {
    HashMap<String, Object> uploadAliVideo(MultipartFile file, String title);

    Boolean deleteVideoById(String videoId);

    Boolean deleteVideoBatch(List<String> videoIdList);

    Map<String, String> getAliVideoPlayAuth(String videoId);
}
