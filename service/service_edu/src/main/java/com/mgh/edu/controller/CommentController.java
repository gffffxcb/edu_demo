package com.mgh.edu.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mgh.commanUtils.MyResult;
import com.mgh.edu.client.CenterClient;
import com.mgh.edu.entity.Comment;
import com.mgh.edu.entity.Member;
import com.mgh.edu.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-27
 */
@RestController("commentController")
@RequestMapping("/edu/comment")
@CrossOrigin
@Slf4j
@Api("评论功能接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CenterClient centerClient;

    @GetMapping("/getComment/{nowPage}/{pageSize}/{courseId}")
    @ApiOperation("获取评论信息根据课程号")
    public MyResult getAllComment(@PathVariable(value = "nowPage") Integer nowPage, @PathVariable(value = "pageSize") Integer pageSize, @PathVariable(value = "courseId") String courseId) {
        Page<Comment> comment = commentService.getCommentByCourseId(nowPage, pageSize, courseId);
        if (comment != null) {
            return MyResult.ok().data("items", comment.getRecords()).data("total", comment.getTotal());
        }
        return MyResult.error();
    }

    @PostMapping("/saveComment")
    @ApiOperation("添加评论信息根据课程ID")
    public Object saveComment(@RequestBody Comment comment) {
        MyResult tokenInfo = centerClient.getInfoByToken();
        log.info("token=" + tokenInfo.getData().toString());
        Map<String, Object> data = tokenInfo.getData();
        Member member = JSONObject.toJavaObject((JSON) JSONObject.toJSON( data.get("items")), Member.class);
        if (member == null) {
            return "redirect:http://localhost:3000/login"; //返回登陆页面重新登陆
        }
        comment.setAvatar(member.getAvatar());
        comment.setNickname(member.getNickname());
        comment.setMemberId(member.getId());
        boolean save = commentService.save(comment);
        if (save) {
            return MyResult.ok();
        }
        return MyResult.error();
    }
}

