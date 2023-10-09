package com.xiw.test;

/**
 * @author xiwang
 * @date 2022-04-07 09:08
 */
public class MyReflect {

    private String name;

    private int age;

    private MyReflect(int age) {
        this.age = age;
    }
    private static  void staticMethod(){
        System.out.println("this is a static method");
    }

    private void speak(String name) {
        System.out.println("My name is " + name);
    }

    public MyReflect(String name) {
        this.name = name;
    }
}
