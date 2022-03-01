package com.mgh.commanUtils.to;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author MGH
 * @create 2022-0216 10:06 下午
 */
@ApiModel(value = "课程信息用于显示订单内容", description = "课程信息用于显示订单内容")
@Data
public class OrderInfoCourseTo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "课程ID")
    private String courseId;
    @ApiModelProperty(value = "课程讲师名称")
    private String teacherName;
    @ApiModelProperty(value = "课程标题")
    private String courseTitle;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal totalFee;
    @ApiModelProperty(value = "课程封面图片路径")
    private String courseCover;
}