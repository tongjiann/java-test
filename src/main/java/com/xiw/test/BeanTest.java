package com.xiw.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanTest {
    @Test
    public void test(){
        TestBean testBean = new TestBean(500, 1040);
        TestBean testBean2 = new TestBean(400, 1140);
        TestBean testBean3 = new TestBean(1100, 340);

        List<TestBean> collect = Stream.of(testBean, testBean2, testBean3)
                .sorted(Comparator.comparing(e -> Math.max(e.getA(), e.getB())))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

}
