package com.xiw.pattern.template;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:37
 */
public class ITWorker extends Worker {
    public ITWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "写程序-测bug-fix bug");
    }

    @Override
    public boolean isNeedPrintDate() {
        return true;
    }
}
