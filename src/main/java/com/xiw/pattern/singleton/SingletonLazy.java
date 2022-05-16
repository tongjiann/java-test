package com.xiw.pattern.singleton;

/**
 * @apiNote 懒汉式
 * @author xiwang
 * @since 2022-05-16 11:09
 */
public class SingletonLazy {
    private static final SingletonLazy instance = new SingletonLazy();

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance(){
        return instance;
    }

}
