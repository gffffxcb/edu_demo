package com.mgh.cms.mapper;

import com.mgh.cms.entity.EduChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.cms.entity.vo.ChapterAndVideoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@Repository("eduChapterMapper")
public interface EduChapterMapper extends BaseMapper<EduChapter> {
    List<ChapterAndVideoVo> getAllByCourseId(String id);
}
