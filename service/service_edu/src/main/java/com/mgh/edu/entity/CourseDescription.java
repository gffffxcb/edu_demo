package com.mgh.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_course_description")
@ApiModel(value="CourseDescription对象", description="课程简介描述 ")
@ToString
public class CourseDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
