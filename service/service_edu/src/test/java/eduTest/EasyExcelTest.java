package eduTest;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MGH
 * @create 2022-0216 12:02 下午
 */
@Slf4j
public class EasyExcelTest {
    @Test
    public void demo01(){ //write
        String fileName="/Users/a1989/Desktop/test01.xlsx";
        List<DemoData> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setNo(i);
            data.setName("xhy"+i);
            list.add(data);
        }
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(list);
    }

    @Test
    public void demo02(){ //read
        String fileName="/Users/a1989/Desktop/test.xlsx";
        //doReadAll() 读取所有sheet
        EasyExcel.read(fileName, DemoData.class, new EasyExcelListener()).sheet().doRead();
    }
}
