package com.xiw.mode.observer.code;

import java.util.Observable;
import java.util.Observer;

/**
 * @author xiwang
 * @since 2022-05-13 15:17
 */
public class RealObserver implements Observer {
    public void registerSubject(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SubjectFor3D subjectFor3d) {
            System.out.println("subjectFor3D's msg -- >" + subjectFor3d.getMsg());
        }

        if (o instanceof SubjectForSSQ subjectForSSQ) {
            System.out.println("subjectForSSQ's msg -- >" + subjectForSSQ.getMsg());
        }
    }

}
