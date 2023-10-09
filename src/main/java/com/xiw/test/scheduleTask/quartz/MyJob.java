package com.xiw.test.scheduleTask.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-10-18 09:20
 */
@Slf4j
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("do job.....");
    }
}
