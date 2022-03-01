package makeCode;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author MGH
 * @create 2022-0228 3:15 下午
 */
public class test {

    @Test
    public void demo01() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHH");
        String newDate=sdf.format(new Date());
        System.out.println(hashCodeV);
        System.out.println(newDate);
    }
}
