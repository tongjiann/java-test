package scheduleTask.quartz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-10-18 09:21
 */
@Slf4j
public class SchedulerTest {
    @Test
    public void test() throws SchedulerException, InterruptedException {
        // 获取默认调度器
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        // 创建任务
        JobDetail jobDetail = JobBuilder
                .newJob(MyJob.class)
                .withIdentity("myJob", "myGroup")
                .build();

        // 创建简单调度器
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(3)
                .repeatForever();

        // 创建触发器
        SimpleTrigger simpleTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "myGroup")
                .withSchedule(simpleScheduleBuilder)
                .startNow()
                .build();

        // 注册任务
        Date date = defaultScheduler.scheduleJob(jobDetail, simpleTrigger);

        // 启动
        defaultScheduler.start();

        Thread.sleep(9000);

        // 关闭任务
        defaultScheduler.shutdown(true);

        log.info(String.valueOf(date));
    }
}
