package com.xiw.pattern.singleton;

import org.junit.Test;

public class SimpleSingletonTest {

    @Test
    public void getSingletonLazy() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonLazy.getInstance());
        }
    }

    @Test
    public void getSingletonLazyPlus() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonLazyPlus.getInstance());
        }
    }

    @Test
    public void getSingletonThreadNotSafe() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonThreadNotSafe.getInstance());
        }
    }

    @Test
    public void getSingletonSlow() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonSlow.getInstance());
        }
    }

    @Test
    public void getSingletonThreadNotSafePlus() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonThreadNotSafePlus.getInstance());
        }
    }

    @Test
    public void getSingletonDoubleCheck() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonDoubleCheck.getInstance());
        }
    }

    @Test
    public void getSingletonInnerClass() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonInnerClass.getInstance());
        }
    }


    @Test
    public void getSingletonEnum() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SingletonEnum.instance.method());
        }
    }
}