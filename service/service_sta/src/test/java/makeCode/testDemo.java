package makeCode;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author MGH
 * @create 2022-0301 4:20 下午
 */
public class testDemo {
    @Test
    public void demo01(){
        Date date = DateUtils.addDays(new Date(), -1);
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(date);
        System.out.println(format);

    }
}
