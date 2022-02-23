package com.mgh.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.edu.entity.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
public interface VideoService extends IService<Video> {

    String addVideo(Video video);

    Boolean updateVideo(Video video);

    Boolean deleteVideo(String videoId);
    Boolean deleteVideoByCourseId(String courseId);
}
