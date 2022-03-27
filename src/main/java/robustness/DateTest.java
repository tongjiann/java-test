package robustness;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        getTime();
    }

    private static void getTime() {
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
        // 获取纳秒，但是开始时间与currentTimeMillis不同，所以输出时间不同
        System.out.println(System.nanoTime());
        System.out.println(Instant.now().getNano());
    }

    @Test
    public void testSeconds() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssS");
        for (int i = 0; i < 10000; i++) {
            String format = sdf.format(new Date());
            System.out.println(format);

        }
    }
}
