package com.mgh.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author MGH
 * @create 2022-0210 4:26 下午
 */
@Data
@ApiModel("teacher查询条件")
@ToString
public class TeacherQuery {
    @ApiModelProperty("教师名称")
    private String name;
    @ApiModelProperty("教师职级")
    private Integer level;
    @ApiModelProperty("开始日期")
    private String begin;
    @ApiModelProperty("结束日期")
    private String end;
}
