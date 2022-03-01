package com.mgh.edu.mapper;

import com.mgh.edu.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@Repository("commentMapper")
public interface CommentMapper extends BaseMapper<Comment> {

}
