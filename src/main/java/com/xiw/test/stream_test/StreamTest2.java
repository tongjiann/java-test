package com.xiw.test.stream_test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest2 {

    /**
     * stream类型有两种，一种是顺序的stream，还有一种是并行的parallelStream
     * <p>
     * 两者最主要的区别在于stream是按Collection的顺序依次执行，
     * parallelStream是将Collection进行分块，然后每一块同时执行
     *
     * @see StreamTest2#thePrincipeOfParallelStream()
     */
    @Test
    public void streamTypeTest() {
        // nothing
    }

    @Test
    public void thePrincipeOfParallelStream() {
        List<String> collection = Arrays.asList("example", "example", "example", "example");
        List<List<String>> partition = Lists.partition(collection, 2);
        List<Runnable> collect = partition.stream().map(this::getRunnable).collect(Collectors.toList());
        collect.forEach(Runnable::run);
    }

    @Test
    public void performanceBetweenTwo() {
        long t0 = System.nanoTime();

        // 初始化一个范围1E整数流,求能被2整除的数字，toArray()是终点方法

        int a[]=IntStream.range(0, 100_000_000).filter(p -> p % 2==0).toArray();


        long t1 = System.nanoTime();


        int b[] = IntStream.range(0, 100_000_000).parallel().filter(p -> p % 2 == 0).toArray();

        long t2 = System.nanoTime();

        // 我本机的结果是serial: 0.22s, parallel 0.08s

        System.out.printf("serial: %.2fs, parallel %.2fs%n", (t1 - t0) * 1e-9, (t2 - t1) * 1e-9);
    }

    private Runnable getRunnable(List<String> stringList) {
        return () -> System.out.println(stringList);
    }

}
