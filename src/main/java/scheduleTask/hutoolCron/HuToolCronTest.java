package scheduleTask.hutoolCron;

import cn.hutool.cron.CronUtil;
import org.junit.Test;

import java.util.UUID;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-10-18 09:57
 */
public class HuToolCronTest {
    @Test
    public void test() throws InterruptedException {
        // 开启秒匹配
        CronUtil.setMatchSecond(true);
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().toUpperCase();
            MyTask myTask = new MyTask(uuid);
            CronUtil.schedule(uuid, "0/1 * * * * ?", myTask);
        }
        CronUtil.start();
        Thread.sleep(4000);
    }
}
