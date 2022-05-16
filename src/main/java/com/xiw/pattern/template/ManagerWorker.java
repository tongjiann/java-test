package com.xiw.pattern.template;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:37
 */
public class ManagerWorker extends Worker {
    public ManagerWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
		System.out.println(name + "打dota...");
    }
}
