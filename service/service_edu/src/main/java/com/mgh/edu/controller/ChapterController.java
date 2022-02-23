package com.mgh.edu.controller;


import com.mgh.commanUtils.MyResult;
import com.mgh.edu.entity.Chapter;
import com.mgh.edu.entity.vo.ChapterVo;
import com.mgh.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@RestController("chapterController")
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/getAll/{id}")//获取所有章节小结
    public MyResult getChapterByCourse(@PathVariable(required = true, name = "id") String courseId) {
        List<ChapterVo> list = chapterService.getAllByCourse(courseId); //获取所有章节小结
        return MyResult.ok().data("items", list);
    }

    @PostMapping //添加章节
    public MyResult saveChapter(@RequestBody Chapter chapter) {
        Boolean flag = chapterService.saveChapter(chapter);
        if (flag){
            return MyResult.ok().message("添加章节成功");
        }
        return MyResult.error().message("添加章节失败");
    }
    @GetMapping("/{id}")//根据id获取章节
    public MyResult getChapter(@PathVariable(name = "id") String id) {
        Chapter chapter = chapterService.getById(id);
        if (chapter!=null){
            return MyResult.ok().data("items", chapter);
        }
        return MyResult.error().message("获取id为"+id+"的章节信息失败");
    }
    @PutMapping //修改章节
    public MyResult updateChapter(@RequestBody Chapter chapter) {
        Boolean flag = chapterService.updateChapter(chapter);
        if (flag){
            return MyResult.ok().message("修改章节成功");
        }
        return MyResult.error().message("修改章节失败");
    }

    @DeleteMapping("/{id}") //根据id删除章节
    public MyResult deleteChapter(@PathVariable(name = "id") String id) {
        Boolean flag = chapterService.deleteChapter(id);
        if (flag){
            return MyResult.ok().message("删除章节成功");
        }
        return MyResult.error().message("该章节下存在小节内容，请删除小节后再操作！");

    }

}

