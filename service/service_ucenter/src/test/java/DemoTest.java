import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * @author MGH
 * @create 2022-0224 2:44 下午
 */
public class DemoTest {
    @Test
    public void demo01(){
        String s = DigestUtils.md5DigestAsHex("123456".getBytes()); //md5加密
        System.out.println(s);
    }
}
