package com.xiw.test.robustness;

import java.util.*;
import java.util.stream.Collectors;

import static com.xiw.test.robustness.ArrayListTest.SPLIT;

public class CodePerformanceTest {
    public static void main(String[] args) {
//        collectionEmptyTest();
//        resizeTest();
        for (int i = 0; i < 20; i++) {
            setTest();
        }
    }

    /**
     * 利用Set元素唯一的特性，可以快速对另一个集合进行去重操作，避免使用{@link List#contains}进行遍历去重或者判断包含操作
     * <p>第一点：转成set再转回去时间比遍历要慢？（可能是由于数据量）</p>
     * <p>第二点：进行多次测试的时候，第一次时间远远高于后面几次的时间（使用for测试）</p>
     */
    private static void setTest() {
        System.out.println(SPLIT);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2322");
        list.add("522");
        list.add("2");
        list.add("2523");
        list.add("2");
        list.add("2");
        list.add("2225352");
        list.add("2");
        list.add("22352323");
        list.add("22235");
        list.add("23552235");
        list.add("3");
        list.add("4");
        long start1 = System.nanoTime();
        List<String> strings = new ArrayList<>(new HashSet<>(list));
        long end1 = System.nanoTime();
        System.out.println((end1 - start1) + ":" + strings);
        Iterator<String> iterator = list.iterator();
        List<String> strings1 = new ArrayList<>();
        long start2 = System.nanoTime();

        while (iterator.hasNext()) {
            String next = iterator.next();
            while (!strings1.contains(next)) {
                strings1.add(next);
            }
        }
        long end2 = System.nanoTime();
        System.out.println((end2 - start2) + ":" + strings1);

        List<String> strings3 = new ArrayList<>();
        long start4 = System.nanoTime();
        for (String s : list) {
            while (!strings3.contains(s)) {
                strings3.add(s);
            }
        }
        long end4 = System.nanoTime();
        System.out.println((end4 - start4) + ":" + strings3);

        long start3 = System.nanoTime();
        List<String> strings2 = list.stream().distinct().collect(Collectors.toList());
        long end3 = System.nanoTime();
        System.out.println((end3 - start3) + ":" + strings2);


    }

    /**
     * 集合初始化时，指定集合初始值大小。
     *
     * <p> HashMap使用如下构造方法进行初始化，如果暂时无法确定集合大小，那么指定默认值（16）即可；</p>
     * <p> 如果hashMap存放元素较多，由于没有设置容量初始大小，随着元素增加而被迫不断扩容，resize()方法不断调用，反复重建哈希表和数据迁移。当放置的集合元素个数达千万级时会影响程序性能。</p>
     */
    private static void resizeTest() {
    }

    /**
     * 集合为空判断的方法要用{@link List#isEmpty()}而不要用{@link List#size()}
     * <p>因为前者的时间复杂度为O(1),后者复杂度为O(n)</p>
     */
    private static void collectionEmptyTest() {
        List<String> list = new ArrayList<>();
        System.out.println(list.isEmpty());
        System.out.println(list.size() == 0);
    }
}
