package com.mgh.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.edu.entity.Chapter;
import com.mgh.edu.entity.Video;
import com.mgh.edu.entity.vo.ChapterVo;
import com.mgh.edu.mapper.ChapterMapper;
import com.mgh.edu.mapper.VideoMapper;
import com.mgh.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Service("chapterServiceImpl")
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterMapper mapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<ChapterVo> getAllByCourse(String courseId) {
        List<ChapterVo> voList = mapper.getAllByCourseId(courseId);
        return voList;
    }

    @Override
    public Boolean saveChapter(Chapter chapter) {
        int insert = mapper.insert(chapter);
        if (insert==1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateChapter(Chapter chapter) {
        int insert = mapper.updateById(chapter);
        if (insert==1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteChapter(String id) {
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id",id);
        int vCount = videoMapper.selectCount(videoWrapper);
        if (vCount==0){
            int i = mapper.deleteById(id);
            return true;
        }
            return false;
    }
}
