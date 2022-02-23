package com.mgh.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.edu.entity.Video;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Repository("videoMapper")
public interface VideoMapper extends BaseMapper<Video> {

}
