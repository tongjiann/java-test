package streamTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        List<String> stringList = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
//        foreachFindMatchTest(integerList);
//        filterTest(integerList);
//        maxMinCountTest(stringList);
        case16();
    }


    // 3.1 遍历/匹配（foreach/find/match）

    public static void foreachFindMatchTest(List<Integer> list) {
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }

    // 3.2 筛选（filter）


    /**
     * <p>案例一：筛选出Integer集合中大于7的元素，并打印出来</p>
     */
    public static void filterTest(List<Integer> list) {
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 7).forEach(System.out::println);
    }

    /**
     * <p>案例二：筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集），后文有详细介绍。</p>
     */
    public static void case1() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());
        System.out.print("高于8000的员工姓名：" + fiterList);
    }

    /**
     * <p>案例一：获取String集合中最长的元素。</p>
     */
    public static void maxMinCountTest(List list) {
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        boolean present = max.isPresent();
        if (!present) {
            try {
                throw new Exception("the result is not present");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("最长的字符串：" + max.get());
        }
    }

    /**
     * <p>案例二：获取Integer集合中的最大值。</p>
     */
    public static void case2() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自然排序的最大值：" + max.get());
        System.out.println("自定义排序的最大值：" + max2.get());
    }

    /**
     * <p>案例三：获取员工工资最高的人。</p>
     */
    public static void case3() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值：" + max.get().getSalary());
    }

    /**
     * <p>案例四：计算Integer集合中大于6的元素的个数。</p>
     */
    public static void case4() {
        List<Integer> list = Arrays.asList(7, 6, 4, 8, 2, 11, 9);

        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数：" + count);

    }

    // 3.4 映射(map/flatMap)

    /**
     * <p>案例5：英文字符串数组的元素全部改为大写。整数数组每个元素+3。</p>
     * <p>每个元素大写：[ABCD, BCDD, DEFDE, FTR]</p>
     * <p>每个元素+3：[4, 6, 8, 10, 12, 14]</p>
     */
    public static void case5() {
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);
    }

    /**
     * <p>案例6：将员工的薪资全部增加1000。</p>
     * <p>一次改动前：Tom–>8900</p>
     * <p>一次改动后：Tom–>18900</p>
     * <p>二次改动前：Tom–>18900</p>
     * <p>二次改动后：Tom–>18900</p>
     */
    public static void case6() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList
                .stream()
                .peek(person -> person.setSalary(person.getSalary() + 10000))
                .collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());
    }

    /**
     * <p>案例三：将两个字符数组合并成一个新的字符数组。</p>
     * <p>处理前的集合：[m-k-l-a, 1-3-5]</p>
     * <p>处理后的集合：[m, k, l, a, 1, 3, 5]</p>
     */
    public static void case7() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    /*
      3.5 归约(reduce)
      归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     */

    /**
     * <p>案例一：求Integer集合的元素之和、乘积和最大值。</p>
     * <p>list求和：29,29,29</p>
     * <p>list求积：2112</p>
     * <p>list求和：11,11</p>
     */
    public static void case8() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
    }


    /**
     * <p>案例二：求所有员工的工资之和和最高工资。</p>
     * <p>工资之和：49300,49300,49300</p>
     * <p>最高工资：9500,9500</p>
     */
    public static void case9() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
                (sum1, sum2) -> sum1 + sum2);
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        // 求最高工资方式1：
        Integer maxSalary = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                Integer::max);
        // 求最高工资方式2：
        Integer maxSalary2 = personList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                (max1, max2) -> max1 > max2 ? max1 : max2);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);
    }

    // 3.6 收集(collect)

    /*
        3.6.1 归集(toList/toSet/toMap)
        因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。
        toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
     */

    /**
     * <p>toList：[6, 4, 6, 6, 20]</p>
     * <p>toSet：[4, 20, 6]</p>
     * <p>toMap：{Tom=mutest.Person@5fd0d5ae, Anni=mutest.Person@2d98a335}</p>
     */
    public static void case10() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));

        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        // @Data注释默认重写了toString()
        System.out.println("toMap:" + map);
    }

    /*
        3.6.2 统计(count/averaging)
        Collectors提供了一系列用于数据统计的静态方法：
        计数：count
        平均值：averagingInt、averagingLong、averagingDouble
        最值：maxBy、minBy
        求和：summingInt、summingLong、summingDouble
        统计以上所有：summarizingInt、summarizingLong、summarizingDouble
     */

    /**
     * 案例：统计员工人数、平均工资、工资总额、最高工资。
     * <p>员工总数：3</p>
     * <p>员工平均工资：7900.0</p>
     * <p>员工工资总和：23700</p>
     * <p>员工工资所有统计：DoubleSummaryStatistics{count=3, sum=23700.000000,min=7000.000000, average=7900.000000, max=8900.000000}</p>
     */
    public static void case11() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 求总数
        // Long count = personList.stream().count();
        Long count = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        // Optional<Integer> max = personList.stream().map(Person::getSalary).max(Integer::compare);
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        // Integer sum = personList.stream().mapToInt(Person::getSalary).sum();
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }

    /*
        3.6.3 分组(partitioningBy/groupingBy)

        分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
        分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组。
     */

    /**
     * <p>案例：将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组</p>
     * <p>员工按薪资是否大于8000分组情况：{false=[mutest.Person@2d98a335, mutest.Person@16b98e56, mutest.Person@7ef20235], true=[mutest.Person@27d6c5e0, mutest.Person@4f3f5b24, mutest.Person@15aeb7ab]}</p>
     * <p>员工按性别分组情况：{female=[mutest.Person@16b98e56, mutest.Person@4f3f5b24, mutest.Person@7ef20235], male=[mutest.Person@27d6c5e0, mutest.Person@2d98a335, mutest.Person@15aeb7ab]}</p>
     * <p>员工按性别、地区：{female={New York=[mutest.Person@4f3f5b24, mutest.Person@7ef20235], Washington=[mutest.Person@16b98e56]}, male={New York=[mutest.Person@27d6c5e0, mutest.Person@15aeb7ab], Washington=[mutest.Person@2d98a335]}}</p>
     */
    public static void case12() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, Map<Integer, List<Person>>>> group2 = personList
                .stream()
                .collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea, Collectors.groupingBy(Person::getAge))));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }

    /*
        3.6.4 接合(joining)
        joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     */

    /**
     * <p>所有员工的姓名：Tom,Jack,Lily</p>
     * <p>拼接后的字符串：A-B-C</p>
     */
    public static void case13() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /*
        3.6.5 归约(reducing)
        Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     */

    /**
     * <p>员工扣税薪资总和：8700</p>
     * <p>员工薪资总和：23700</p>
     */
    public static void case14() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    /*
        3.7 排序(sorted)
        sorted，中间操作。有两种排序：
        sorted()：自然排序，流中元素需实现Comparable接口
        sorted(Comparator com)：Comparator排序器自定义排序
     */

    /**
     * <p>案例：将员工按工资由高到低（工资一样则按年龄由大到小）排序</p>
     * <p>按工资升序排序：[Lily, Tom, Sherry, Jack, Alisa]</p>
     * <p>按工资降序排序：[Sherry, Jack, Alisa, Tom, Lily]</p>
     * <p>先按工资再按年龄升序排序：[Lily, Tom, Sherry, Jack, Alisa]</p>
     * <p>先按工资再按年龄自定义降序排序：[Alisa, Jack, Sherry, Tom, Lily]</p>
     */
    public static void case15() {
        List<Person> personList = new ArrayList<Person>();

        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));

        // 按工资升序排序（自然排序）
        List<String> newList = personList
                .stream()
                .sorted(Comparator.comparing(Person::getSalary))
                .map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList
                .stream()
                .sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList
                .stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge))
                .map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList
                .stream()
                .sorted((p1, p2) -> {
                    if (p1.getSalary() == p2.getSalary()) {
                        return p2.getAge() - p1.getAge();
                    } else {
                        return p2.getSalary() - p1.getSalary();
                    }
                })
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    /*
        3.8 提取/组合
        流也可以进行合并、去重、限制、跳过等操作。
     */

    /**
     * <p>流合并：[a, b, c, d, e, f, g]</p>
     * <p>limit：[1, 3, 5, 7, 9, 11, 13, 15, 17, 19]</p>
     * <p>skip：[3, 5, 7, 9, 11]</p>
     */
    public static void case16() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }

}

