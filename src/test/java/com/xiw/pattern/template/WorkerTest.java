package com.xiw.pattern.template;

import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:39
 */
public class WorkerTest {
    @Test
    public void templateTest(){
        Worker it1 = new ITWorker("xiw");
		it1.workOneDay();
		Worker it2 = new ITWorker("老张");
		it2.workOneDay();
		Worker hr = new HRWorker("迪迪");
		hr.workOneDay();
		Worker qa = new TestWorker("老李");
		qa.workOneDay();
		Worker pm = new ManagerWorker("坑货");
		pm.workOneDay();

    }
}
