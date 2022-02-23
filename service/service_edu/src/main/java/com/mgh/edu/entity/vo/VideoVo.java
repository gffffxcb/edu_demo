package com.mgh.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author MGH
 * @create 2022-0217 7:48 下午
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer sort;
    private Boolean free;
}