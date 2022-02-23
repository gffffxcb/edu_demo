package com.mgh.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.edu.entity.Chapter;
import com.mgh.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getAllByCourse(String courseId);

    Boolean saveChapter(Chapter chapter);

    Boolean updateChapter(Chapter chapter);

    Boolean deleteChapter(String id);
}
