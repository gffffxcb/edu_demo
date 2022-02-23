package com.mgh.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.mgh.oss.service.OssService;
import com.mgh.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author MGH
 * @create 2022-0215 3:39 下午
 */
@Service("ossServiceImpl")
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //为文件名添加随机值
        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String filename = "edu-online/avatar/" +date+"/"+uuid+file.getOriginalFilename(); //文件名称 使用日期分文件夹以及随机值名称
        String url = "https://" + bucketName + "." + endpoint + "/" + filename;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build("https://" + endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream = file.getInputStream(); //文件输入流
            // 创建PutObject请求。
            ossClient.putObject(bucketName, filename, inputStream);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
