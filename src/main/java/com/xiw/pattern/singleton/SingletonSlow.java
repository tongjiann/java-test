package com.xiw.pattern.singleton;

/**
 * @author xiwang
 * @apiNote 线程安全但效率低下的饿汉式
 * @since 2022-05-16 11:09
 */
public class SingletonSlow {


    private static SingletonSlow instance = null;


    private SingletonSlow() {
    }

    public static synchronized SingletonSlow getInstance() {
        if (instance == null) {
            instance = new SingletonSlow();
        }
        return instance;
    }

}
