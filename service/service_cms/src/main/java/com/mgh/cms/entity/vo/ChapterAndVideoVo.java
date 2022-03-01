package com.mgh.cms.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MGH
 * @create 2022-0227 11:33 上午
 */
@Data
@ApiModel("章节和小节信息")
public class ChapterAndVideoVo implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();
}