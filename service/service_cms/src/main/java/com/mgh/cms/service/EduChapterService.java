package com.mgh.cms.service;

import com.mgh.cms.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.cms.entity.vo.ChapterAndVideoVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterAndVideoVo> getChapterAndVideoInfo(String courseId);
}
