package com.xiw.pattern.singleton;

/**
 * @author xiwang
 * @apiNote 线程安全但效率低下的饿汉式的改进版，但是还是线程不安全
 *
 * @since 2022-05-16 11:09
 */
public class SingletonThreadNotSafePlus {


    private static SingletonThreadNotSafePlus instance = null;


    private SingletonThreadNotSafePlus() {
    }

    public static SingletonThreadNotSafePlus getInstance() {
        if (instance == null) {
            synchronized (SingletonThreadNotSafePlus.class){
                instance = new SingletonThreadNotSafePlus();
            }
        }
        return instance;
    }

}
