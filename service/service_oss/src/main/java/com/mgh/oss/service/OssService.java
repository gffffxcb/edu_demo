package com.mgh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author MGH
 * @create 2022-0215 3:38 下午
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
