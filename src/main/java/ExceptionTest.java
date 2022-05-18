import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-17 19:58
 */
public class ExceptionTest {
    @Test
    public void oomTest(){
        List<Object> list = new LinkedList<>();
        while (true) {
            byte[] b = new byte[1024*1024];
            list.add(b);
        }
    }

    @Test
    public void stackOverflowTest(){
        stackOverflowTest();
    }
}
