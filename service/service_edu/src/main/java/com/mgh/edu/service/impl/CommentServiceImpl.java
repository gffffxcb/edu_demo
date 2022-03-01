package com.mgh.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.edu.entity.Comment;
import com.mgh.edu.mapper.CommentMapper;
import com.mgh.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<Comment> getCommentByCourseId(Integer nowPage, Integer pageSize, String courseId) {
        Page<Comment> page = new Page<>(nowPage, pageSize);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        Page<Comment> commentPage = commentMapper.selectPage(page, queryWrapper);
        return commentPage;
    }
}
