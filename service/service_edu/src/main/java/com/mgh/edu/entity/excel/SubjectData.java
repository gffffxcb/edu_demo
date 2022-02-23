package com.mgh.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author MGH
 * @create 2022-0216 2:40 下午
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName; //第一列
    @ExcelProperty(index = 1)
    private String twoSubjectName; //第二列
}
