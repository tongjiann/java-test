package com.xiw.pattern.template;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:37
 */
public class HRWorker extends Worker {
    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
		System.out.println(name + "看简历-打电话-接电话");
    }
}
