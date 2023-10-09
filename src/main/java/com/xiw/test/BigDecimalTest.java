package com.xiw.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-06-06 16:48
 */
public class BigDecimalTest {
    @Test
    public void zeroTest() {
        System.out.println(BigDecimal.ZERO.compareTo(BigDecimal.ONE));
    }

    /**
     * 使用new BigDecimal()和BigDecimal.valueOf(）构造double出来的值
     * 但是将double转为String类型(不是使用toString())，会改变精度
     */
    @Test
    public void longTest(){
        Double d = 2.345531;
        String s = "2.3455345434536623436336437";
        System.out.println(new BigDecimal(s));
        BigDecimal bdStr = new BigDecimal((d.toString()));
        BigDecimal bdLog = BigDecimal.valueOf(d);
        System.out.println(bdStr);
        System.out.println(bdLog);
    }

    @Test
    public void test2(){
        new BigDecimal("4").divide(new BigDecimal("3"));
    }

    @Test
    public void test3(){
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2")));
    }
}
