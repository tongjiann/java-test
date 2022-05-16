package com.xiw.pattern.singleton;

/**
 * @author xiwang
 * @apiNote 饿汉式
 * @since 2022-05-16 11:09
 */
public class SingletonLazyPlus {


    private static SingletonLazyPlus instance = null;

    static {
        instance = new SingletonLazyPlus();
    }

    private SingletonLazyPlus() {
    }

    public static SingletonLazyPlus getInstance() {
        return instance;
    }

}
