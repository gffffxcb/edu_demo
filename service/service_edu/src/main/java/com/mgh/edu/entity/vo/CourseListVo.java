package com.mgh.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author MGH
 * @create 2022-0220 11:25 上午
 */
@ApiModel(value = "course列表", description = "课程列表信息")
@Data
public class CourseListVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "课程讲师姓名")
    private String teacherName;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
    @ApiModelProperty(value = "销售数量")
    private Long buyCount;
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;
}