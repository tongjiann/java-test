package com.xiw.mode.observer.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiwang
 * @since 2022-05-13 14:21
 */
public class SubjectFor3D implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    private String messageFor3D;

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(observer -> {
            observer.udpate(messageFor3D);
        });
    }

    public void setMessageFor3D(String messageFor3D) {
        this.messageFor3D = messageFor3D;
        notifyObservers();
    }
}
