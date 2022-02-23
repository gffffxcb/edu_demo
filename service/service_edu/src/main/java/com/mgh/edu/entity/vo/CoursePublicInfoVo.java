package com.mgh.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author MGH
 * @create 2022-0218 11:16 下午
 */
@ApiModel(value = "发布时回显数据给前台", description = "发布时回显数据给前台")
@Data
public class CoursePublicInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "课程讲师姓名")
    private String teacherName;
    @ApiModelProperty(value = "一级课程专业")
    private String oneSubjectTitle;
    @ApiModelProperty(value = "二级课程专业")
    private String twoSubjectTitle;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "课程简介")
    private String description;
}