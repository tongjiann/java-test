package com.xiw.test;

import org.junit.Test;

public class IntegerTest {
    public static final Integer NUMBER_1 = 1;
    public static final Integer NUMBER_256 = 256;

    @Test
    public void divisibleTest() {
        System.out.println(8 / 2);
    }

    @Test
    public void modTest() {
        System.out.println(9 % 2);
    }

    @Test
    public void nullTest(){
        Integer i = null;
        System.out.println(1==i);
    }

    @Test
    public void oneTest(){
        Integer one = new Integer(1);
        System.out.println(one.equals(1));
        System.out.println(one.equals(NUMBER_1));
        System.out.println(NUMBER_1.equals(1));

        Integer number256 = new Integer(256);
        System.out.println(one.equals(256));
        System.out.println(one.equals(NUMBER_256));
        System.out.println(NUMBER_256.equals(256));

    }

    @Test
    public void parseNullTest(){
        int i = Integer.valueOf(null);
    }
}