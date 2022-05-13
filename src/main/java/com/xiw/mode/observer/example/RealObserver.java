package com.xiw.mode.observer.example;

/**
 * @author xiwang
 * @since 2022-05-13 14:25
 */
public class RealObserver implements Observer {
    private String observerName;
    private Subject subject;

    public RealObserver(String observerName, Subject subject) {
        this.observerName = observerName;
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void udpate(String msg) {
        System.out.println(this.observerName + " " + "获取到新的信息:" + msg);
    }
}
