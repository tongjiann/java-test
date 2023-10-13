package com.xiw.test.stream_test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaExpressionTest {

    private static final List<String> NAME_LIST = Arrays.asList("Jam", "Sam", "Cook", "Tony");

    /**
     * Lambda表达式是写出更优雅的 Java 代码，尤其在集合的遍历和其他集合操作中，可以极大地优化代码结构的一种表达式,
     * 在java编译的时候将lambda表达式编译为一个匿名内部类来使用
     * <p>
     * 也可以成为闭包或者匿名函数
     * <p>
     * 它有两种用法：
     * <pre>
     * (parameters) -> expression
     * (parameters) ->{ statements; }
     * </pre>
     */
    public void whatIsLambdaExpression() {
        // nothing
    }

    @Test
    @SuppressWarnings("all")
    public void difference() {
        // 老式的写法
        Runnable oldExpression = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running without Lambda");
            }
        };

        // 新式的写法
        Runnable newExpression = () -> System.out.println("Running with Lambda");
    }

    @Test
    public void foreachTest() {
        // old
        for (String s : NAME_LIST) {
            System.out.println(s);
        }

        // new
        NAME_LIST.forEach(System.out::println);
    }

    @Test
    public void onlyUseFinalVariables() {
        final String s = "0";
        NAME_LIST.forEach(name -> name = name + s);
    }

    @Test
    public void cannotChangeOutVariables() {
        int i = 0;
        // NAME_LIST.forEach(name -> i = i + 1);
    }

    @Test
    public void methodReferenceTest() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Tom"));
        List<String> nameList = people
                .stream()
                // method reference
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println(nameList);
    }

    @Test
    public void newForeachMethodTest() {
        List<String> skills = Arrays.asList("java", "golang", "c++", "c", "python");
        // before
        for (String skill : skills) {
            System.out.print(skill + ",");
        }
        System.out.println();

        // ================================================

        // after
        // forEach with lambda
        skills.forEach((skill) -> System.out.print(skill + ","));
        System.out.println();

        // forEach with method reference
        skills.forEach(System.out::print);
    }

    @Test
    public void distinctWithLambdaTest() {
        List<String> notDistinctedList = Arrays.asList("one", "two", "three", "three");
        System.out.println(notDistinctedList);

        List<String> distinctedList = notDistinctedList.stream().distinct().collect(Collectors.toList());

        System.out.println(distinctedList);
    }

}
