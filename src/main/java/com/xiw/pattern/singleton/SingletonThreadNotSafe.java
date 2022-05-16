package com.xiw.pattern.singleton;

/**
 * @author xiwang
 * @apiNote 线程不安全的饿汉式
 * @since 2022-05-16 11:09
 */
public class SingletonThreadNotSafe {


    private static SingletonThreadNotSafe instance = null;


    private SingletonThreadNotSafe() {
    }

    public static SingletonThreadNotSafe getInstance() {
        if (instance == null) {
            instance = new SingletonThreadNotSafe();
        }
        return instance;
    }

}
