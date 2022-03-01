package com.mgh.cms.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author MGH
 * @create 2022-0225 10:42 下午
 * 课程页面检索条件
 */
@Data
public class CoursePageQuery {
    @ApiModelProperty(value = "二级分类")
    private String twoSubjectId;
    @ApiModelProperty(value = "价格排序，1升使用，0不使用")
    private Integer priceSort;
    @ApiModelProperty(value = "浏览数量排序，1降序 0不使用")
    private Integer viewCountSort;
    @ApiModelProperty(value = "更新时间排序，1降序 0不使用")
    private Integer gmtModifiedSort;
}
