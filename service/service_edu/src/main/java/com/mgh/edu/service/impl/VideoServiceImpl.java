package com.mgh.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.commanUtils.MyResult;
import com.mgh.edu.client.VodClient;
import com.mgh.edu.entity.Video;
import com.mgh.edu.mapper.VideoMapper;
import com.mgh.edu.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Service("videoService")
@Slf4j
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VodClient vodClient;

    @Override
    public String addVideo(Video video) {
        int flag = videoMapper.insert(video);
        if (flag==1){
            return video.getId();
        }
        return null;
    }

    @Override
    public Boolean updateVideo(Video video) {
        int i = videoMapper.updateById(video);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteVideo(String videoId) {
        int i = videoMapper.deleteById(videoId);
        if (i==1){
            return true;
        }
        return false;
    }

    @Override //删除云端视频
    public Boolean deleteVideoByCourseId(String courseId) {
        QueryWrapper<Video> videoQuery = new QueryWrapper<>();
        videoQuery.select("video_source_id");
        videoQuery.eq("course_id", courseId);
        List<Video> videos = videoMapper.selectList(videoQuery);
        if (!videos.isEmpty()){
            ArrayList<String> arrayList = new ArrayList<>();
            for (Video v:videos) {
                arrayList.add(v.getVideoSourceId());
            }
            MyResult myResult = vodClient.deleteVideoBatch(arrayList);
            if (myResult.getSuccess()){
                return true;
            }
        }
        return false;
    }
}
