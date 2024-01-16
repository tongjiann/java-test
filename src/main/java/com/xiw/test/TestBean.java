package com.xiw.test;

public class TestBean {

    private int a;

    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public TestBean(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

}
