package com.xiw.pattern.observer.example;

/**
 * @author xiwang
 * @date 2022-05-13 14:17
 */
public interface Subject {

    /**
     * 注册一个观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有的观察着
     */
        void notifyObservers();

}
