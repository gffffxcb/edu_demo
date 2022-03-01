package com.mgh.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.edu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
public interface CommentService extends IService<Comment> {

    Page<Comment> getCommentByCourseId(Integer nowPage, Integer pageSize, String courseId);
}
