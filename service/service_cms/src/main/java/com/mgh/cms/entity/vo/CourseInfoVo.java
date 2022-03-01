package com.mgh.cms.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author MGH
 * @create 2022-0227 11:19 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "课程信息展示", description = "课程信息")
public class CourseInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private BigDecimal price;
    private Integer LessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String description;
    private String teacherId;
    private String teacherName;
    private String career;
    private String avatar;
}
