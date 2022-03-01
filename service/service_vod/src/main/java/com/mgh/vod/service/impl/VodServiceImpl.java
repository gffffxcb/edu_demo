package com.mgh.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.mgh.serviceBase.exception.MyException;
import com.mgh.vod.service.VodService;
import com.mgh.vod.utils.AliYunVodSDKUtils;
import com.mgh.vod.utils.ConstantPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MGH
 * @create 2022-0221 1:27 下午
 */
@Service("vodServiceImpl")
@Slf4j
public class VodServiceImpl implements VodService {

    @Override
    public HashMap<String, Object> uploadAliVideo(MultipartFile file, String title) {
        try {
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.ACCESS_KEY_ID,
                    ConstantPropertiesUtils.ACCESS_KEY_SECRET, title, file.getOriginalFilename(), file.getInputStream());
            /* 视频分类ID(可选) */
            request.setCateId(ConstantPropertiesUtils.CATE_ID);  //分类节点
            /* 视频描述(可选) */
            //request.setDescription("视频描述");
            /* 封面图片(可选) */
            //request.setCoverURL("http://cover.sample.com/sample.jpg");
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            if (response.isSuccess()) {
                Thread.currentThread().sleep(2000); //等待上传完成后查询信息
                // return  response.getVideoId(); //上传的视频ID
                GetVideoInfoRequest infoRequest = new GetVideoInfoRequest();
                infoRequest.setVideoId(response.getVideoId());
                DefaultAcsClient client = AliYunVodSDKUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
                GetVideoInfoResponse acsResponse = client.getAcsResponse(infoRequest);
                HashMap<String, Object> map = new HashMap<>();
                map.put("videoId", response.getVideoId());
                map.put("videoSize", acsResponse.getVideo().getSize());//视频大小
                // map.put("videoStatus", acsResponse.getVideo().getStatus());//视频状态
                map.put("videoDuration", acsResponse.getVideo().getDuration());//视频时长
                return map;
            } else {
                String errorMessage = "阿里云上传错误:" + "code:" + response.getCode() + ", message:" + response.getMessage();
                log.warn(errorMessage);
                throw new MyException(20001, errorMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(20001, "阿里云上传错误");
        }
    }

    @Override
    public Boolean deleteVideoById(String videoId) {
        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        try {
            DefaultAcsClient client = AliYunVodSDKUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            throw new MyException(20001, e.getLocalizedMessage());
        }
    }

    @Override
    public Boolean deleteVideoBatch(List<String> videoIdList) {
        DeleteVideoRequest request = new DeleteVideoRequest();
        String videoId = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(), ","); //将数组以逗号风格
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        try {
            DefaultAcsClient client = AliYunVodSDKUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            throw new MyException(20001, e.getLocalizedMessage());
        }
    }

    @Override //获取播放凭证
    public Map<String, String> getAliVideoPlayAuth(String videoId) {
        HashMap<String, String> infoMap = new HashMap<>();
        try {
            GetVideoPlayAuthRequest requestAuth = new GetVideoPlayAuthRequest();
            requestAuth.setVideoId(videoId);
            requestAuth.setAuthInfoTimeout(1800L); //设置凭证有效时长 30分钟
            DefaultAcsClient client = AliYunVodSDKUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthResponse responseAuth = client.getAcsResponse(requestAuth);
            //获取playAuth
            infoMap.put("playAuth", responseAuth.getPlayAuth());//playAuth
            responseAuth.getVideoMeta().getCoverURL();
            //Base信息
            infoMap.put("title",responseAuth.getVideoMeta().getTitle());//标题
            infoMap.put("cover",responseAuth.getVideoMeta().getCoverURL());//封面路径
            infoMap.put("videoId", videoId);//videoId
            return infoMap;
        } catch (Exception e) {
            log.error("ErrorMessage = " + e.getLocalizedMessage());
        }
        return null;
    }
}
