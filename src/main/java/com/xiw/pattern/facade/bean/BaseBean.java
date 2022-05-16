package com.xiw.pattern.facade.bean;

/**
 * @author xiwang
 * @since 2022-05-16 15:30
 */
public interface BaseBean {
    default void on() {
        System.out.println("开了");
    }

    default void off() {
        System.out.println("关了");
    }

    default void close() {
        System.out.println("关了");
    }
}
