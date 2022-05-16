package com.xiw.pattern.template;

import java.util.Date;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:37
 */
public abstract class Worker {
    protected String name;

    public Worker(String name) {
        this.name = name;
    }

    /**
     * 记录一天的工作
     */
    public final void workOneDay() {

        System.out.println("-----------------work start ---------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        System.out.println("-----------------work end ---------------");

    }

    /**
     * 工作
     */
    public abstract void work();

    /**
     * 关闭电脑
     */
    private void computerOff() {
        System.out.println(name + "关闭电脑");
    }

    /**
     * 打开电脑
     */
    private void computerOn() {
        System.out.println(name + "打开电脑");
    }

    /**
     * 进入公司
     */
    public void enterCompany() {
        System.out.println(name + "进入公司");
    }

    public boolean isNeedPrintDate() {
        return false;
    }

    /**
     * 离开公司
     */
    public void exitCompany() {
        if (isNeedPrintDate()) {
            System.out.print(new Date().toLocaleString() + "-->");
        }
        System.out.println(name + "离开公司");
    }

}
