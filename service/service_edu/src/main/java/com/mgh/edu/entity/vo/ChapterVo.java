package com.mgh.edu.entity.vo;

/**
 * @author MGH
 * @create 2022-0217 7:48 下午
 */

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();
}