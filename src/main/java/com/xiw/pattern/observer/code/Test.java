package com.xiw.pattern.observer.code;

/**
 * @author xiwang
 * @since 2022-05-13 15:19
 */
public class Test {
    public static void main(String[] args) {
        SubjectFor3D subjectFor3d = new SubjectFor3D();
        SubjectForSSQ subjectForSSQ = new SubjectForSSQ();

        RealObserver observer1 = new RealObserver();
        observer1.registerSubject(subjectFor3d);
        observer1.registerSubject(subjectForSSQ);

        subjectFor3d.setMsg("hello 3d'nums : 110 ");
        subjectForSSQ.setMsg("ssq'nums : 12,13,31,5,4,3 15");

    }
}
