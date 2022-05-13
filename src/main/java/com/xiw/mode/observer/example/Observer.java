package com.xiw.mode.observer.example;

/**
 * @author xiwang
 * @since  2022-05-13 14:12
 */
public interface Observer {
    /**
     * 更新
     *
     * @param msg 更新信息
     */
    void udpate(String msg);
}
