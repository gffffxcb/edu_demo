package eduTest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author MGH
 * @create 2022-0216 12:03 下午
 */
@Data
@ToString
public class DemoData {
    @ExcelProperty("学生编号")
    private Integer no;
    @ExcelProperty("学生姓名")
    private String name;
}
