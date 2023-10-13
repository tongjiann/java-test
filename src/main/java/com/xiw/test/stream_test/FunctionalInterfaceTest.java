package com.xiw.test.stream_test;

import org.junit.Test;

import java.util.function.*;

public class FunctionalInterfaceTest {

    public static final String STRING = "hello world";

    /**
     * 什么是函数式接口
     * <p>
     * FunctionalInterface是只有一个抽象方法的类的注释类
     * <p>
     * 常见的函数式接口有Runnable|Consumer|Function|Supplier|Predicate
     * <p>
     * 后面四个是Java内置四大核心函数式接口，还有一些变种（指定好部分类型）
     */
    public void whatIsFunctionalInterface() {
        // nothing
    }

    /**
     * 消费型接口-accept
     * <p>
     * 实现接口类型的类的特性是传入一个或或者多个对象，返回为空
     * <p>
     * 对传入的对象进行消费，消费完就没了
     *
     * @see java.util.function.Consumer
     * @see java.util.function.BiConsumer
     * @see java.util.function.IntConsumer
     * @see java.util.function.ObjIntConsumer
     */
    @Test
    public void consumerTest() {
        MyConsumer myConsumer = new MyConsumer();
        myConsumer.accept(STRING);
    }

    /**
     * BiConsumer的相当于是传入两个变量的一个消费型接口
     */
    @Test
    public void biConsumerTest() {
        Integer i = 3;
        MyBiConsumer myBiConsumer = new MyBiConsumer();
        myBiConsumer.accept(STRING, i);
    }

    /**
     * 函数型接口-apply()
     * <p>
     * 实现接口类型的类的特性是传入一个或或者多个对象，返回为一个类型对象的方法
     * <p>
     * 提供一个对象，进行处理之后返回一个对象，与stream里面的map()同理
     * <p>
     * 里面有一个常用的方法 <code>Funtion.identity()</code>,作用是将传入的对象原样返回，常用于Collectors.toMap(Object::method,Function.identity())
     *
     * @see java.util.function.Function
     * @see java.util.function.BiFunction
     * @see java.util.function.IntFunction
     * @see java.util.function.ToIntBiFunction
     * @see java.util.function.LongToIntFunction
     */
    @Test
    public void functionTest() {
        MyFunction myFunction = new MyFunction();
        Integer apply = myFunction.apply(STRING);
        System.out.println(apply);
    }

    /**
     * 供给型接口-get()
     * <p>
     * 实现接口类型的类的特性是不传入对象，返回为一个类型的方法
     * <p>
     * 提供一个对象，类似于供应商，提供对象
     *
     * @see java.util.function.Supplier
     * @see java.util.function.IntSupplier
     */
    @Test
    public void supplierTest() {
        MySupplier mySupplier = new MySupplier();
        String s = mySupplier.get();
        System.out.println(s);
    }

    /**
     * 断定型接口-get()
     * <p>
     * 实现接口类型的类的特性是传入一个或或者多个对象，返回为boolean
     * <p>
     * 类似于检测，判断是否符合标准
     *
     * @see java.util.function.Predicate
     * @see java.util.function.BiPredicate
     * @see java.util.function.IntPredicate
     */
    @Test
    public void predicateTest() {
        MyPredicate myPredicate = new MyPredicate();
        String str = "hello";
        boolean test = myPredicate.test(str);
        System.out.println(test);
    }

    /**
     * 自定义的Consumer函数式接口的实现类
     */
    private static class MyConsumer implements Consumer<String> {

        @Override
        public void accept(String s) {
            System.out.println(s);
        }

    }

    /**
     * 自定义的BiConsumer函数式接口的实现类
     */
    static class MyBiConsumer implements BiConsumer<String, Integer> {

        @Override
        public void accept(String s, Integer integer) {
            for (int i = 0; i < integer; i++) {
                System.out.println(s);
            }
        }

    }

    /**
     * 自定义的Function函数式接口的实现类
     */
    static class MyFunction implements Function<String, Integer> {

        @Override
        public Integer apply(String s) {
            return s.length();
        }

    }

    /**
     * 自定义的Supplier函数式接口的实现类
     */
    static class MySupplier implements Supplier<String> {

        @Override
        public String get() {
            return STRING;
        }

    }

    /**
     * 自定义的Predicate函数式接口的实现类
     */
    static class MyPredicate implements Predicate<String> {

        @Override
        public boolean test(String s) {
            return STRING.equals(s);
        }

    }

}
