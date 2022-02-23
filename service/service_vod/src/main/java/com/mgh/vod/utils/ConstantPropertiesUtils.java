package com.mgh.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author MGH
 * @create 2022-0221 1:17 下午
 * 读取配置信息上传vod参数
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    @Value("${aliyun.vod.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.vod.file.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.vod.file.cateId}")
    private Long cateId;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static Long CATE_ID;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        CATE_ID = cateId;
    }
}
