package com.mgh.cms.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author MGH
 * @create 2022-0225 6:24 下午
 */

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "该教师已发布的课程", description = "教师课程")
public class TeacherOfCourseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

}