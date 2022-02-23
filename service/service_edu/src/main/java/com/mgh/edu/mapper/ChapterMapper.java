package com.mgh.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgh.edu.entity.Chapter;
import com.mgh.edu.entity.vo.ChapterVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Repository("chapterMapper")
public interface ChapterMapper extends BaseMapper<Chapter> {
    List<ChapterVo> getAllByCourseId(String id);

}
