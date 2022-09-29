package entity;

import org.junit.Test;

import java.util.Random;


/**
 * @author xiwang
 * @apiNote
 * @since 2022-08-14 09:55
 */
public class OtherTest {
    @Test
    public void test() throws InterruptedException {
        int male = 0;
        int female = 0;
        int current = 0;
        while (true) {
            while (current < 7) {
                int i = (int) Math.round(100 * Math.random());
                System.out.println(i);
                // 0 男 1 女
                if (i == 0) {
                    male++;
                    current = 0;
                    break;
                } else {
                    female++;
                    current++;
                }
            }
//            System.out.println(male + ":" + female);
        }
    }
}
