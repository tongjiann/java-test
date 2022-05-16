package com.xiw.mode.singleton;

/**
 * @author xiwang
 * @apiNote 双重校验，保存线程安全，推荐
 * @since 2022-05-16 11:09
 */
public class SingletonDoubleCheck {


    private static SingletonDoubleCheck instance = null;


    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }

}
