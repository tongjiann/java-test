package com.xiw.pattern.observer.code;

import java.util.Observable;

/**
 * @author xiwang
 * @since 2022-05-13 14:43
 */
public class SubjectForSSQ extends Observable {

    private String msg;


    public String getMsg() {
        return msg;
    }


    /**
     * 主题更新消息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        setChanged();
        notifyObservers();
    }
}