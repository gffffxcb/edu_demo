package com.mgh.oss.controller;

import com.mgh.commanUtils.MyResult;
import com.mgh.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author MGH
 * @create 2022-0215 3:38 下午
 */
@RestController("ossController")
@RequestMapping("/eduOss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/avatar") //头像上传
    public MyResult uploadOssFile(MultipartFile file) {
      String url=  ossService.uploadFileAvatar(file);
        return MyResult.ok().data("path",url);
    }
}
