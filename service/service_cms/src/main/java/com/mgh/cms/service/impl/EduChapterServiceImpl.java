package com.mgh.cms.service.impl;

import com.mgh.cms.entity.EduChapter;
import com.mgh.cms.entity.vo.ChapterAndVideoVo;
import com.mgh.cms.mapper.EduChapterMapper;
import com.mgh.cms.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@Service("eduChapterService")
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduChapterMapper chapterMapper;

    @Override
    public List<ChapterAndVideoVo> getChapterAndVideoInfo(String courseId) {
        List<ChapterAndVideoVo> infoList = chapterMapper.getAllByCourseId(courseId);
        return infoList;
    }
}
