import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class StreamTest {
    @Test
    public void streamVs() {
        List<Integer> list1 = new ArrayList<>(10000);
        List<Integer> list2 = new ArrayList<>(10000);
        List<Integer> list3 = new ArrayList<>(10000);
        Lock lock = new ReentrantLock();

        IntStream.range(0, 10000).forEach(list1::add);

        IntStream.range(0, 10000).parallel().forEach(list2::add);

        IntStream.range(0, 10000).parallel().forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());
    }

    @Test
    public void streamVs2() {
        List<Person> persons = constructPersons();
        doFor(persons);
        doStream(persons);
        doParallelStream(persons);
    }

    /**
     * 构造数据
     *
     * @return
     */
    public List<Person> constructPersons() {

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person(i, "name" + i, "sex" + i, i);
            persons.add(p);
        }
        return persons;
    }

    /**
     * for
     *
     * @param persons
     */
    public void doFor(List<Person> persons) {
        long start = System.currentTimeMillis();

        for (Person p : persons) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //System.out.println(p.name);
        }

        long end = System.currentTimeMillis();
        System.out.println("doFor cost:" + (end - start));
    }

    /**
     * 顺序流
     *
     * @param persons
     */
    public void doStream(List<Person> persons) {
        long start = System.currentTimeMillis();

        persons.stream().forEach(x -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //System.out.println(x.name);
        });

        long end = System.currentTimeMillis();
        System.out.println("doStream cost:" + (end - start));
    }

    /**
     * 并行流
     *
     * @param persons
     */
    public void doParallelStream(List<Person> persons) {

        long start = System.currentTimeMillis();

        persons.parallelStream().forEach(x -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //System.out.println(x.name);
        });

        long end = System.currentTimeMillis();

        System.out.println("doParallelStream cost:" + (end - start));
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2");
        List<Map<String, String>> collect = list.parallelStream().map(StreamTest::string2Map).collect(Collectors.toList());
        System.out.println(collect);
    }

    public static Map<String, String> string2Map(String string) {
        Map<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("key", string);
        return objectObjectHashMap;
    }

    public static void main05(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        System.out.println("test" + list.stream().map(e -> e + 1).collect(Collectors.toList()));
        System.out.println("test" + list);
    }

    public static void main04(String[] args) {
//        String path = ResourceUtils.getURL("classpath:").getPath()+"static/upload";
//        System.out.println(path);
    }

    public static void main03(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        System.out.println(integers);
    }

    public static void main02(String[] args) {
        //经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

        Integer v = list.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(v);   // 300

        Integer v1 = list.stream().reduce(10, (x1, x2) -> x1 + x2);
        System.out.println(v1);  //310

        Integer v2 = list.stream().reduce(0,
                (x1, x2) -> {
                    System.out.println("stream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                },
                (x1, x2) -> {
                    System.out.println("stream combiner: x1:" + x1 + "  x2:" + x2);
                    return x1 * x2;
                });
        System.out.println(v2); // -300

        Integer v3 = list.parallelStream().reduce(0,
                (x1, x2) -> {
                    System.out.println("parallelStream accumulator: x1:" + x1 + "  x2:" + x2);
                    return x1 - x2;
                });
//                (x1, x2) -> {
//                    System.out.println("parallelStream combiner: x1:" + x1 + "  x2:" + x2);
//                    return x1 * x2;
//                });
        System.out.println(v3); //197474048
    }

    public static void main01(String[] args) {
        List<String> list = Arrays.asList("a,b,c", "1,2,3");

        //将每个元素转成一个新的且不带逗号的元素
        Stream<String> s1 = list.stream().map(s -> s.replaceAll(",", ""));
        s1.forEach(System.out::println); // abc  123

        Stream<String> s3 = list.stream().flatMap(s -> {
            //将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        });
        s3.forEach(System.out::println); // a b c 1 2 3
    }

    @Test
    public void nullTest() {
        List<String> list = null;
        list.forEach(e -> {
        });
        List<String> collect = list
                .stream()
                .collect(Collectors.toList());
    }

    @Test
    public void collectAndThenTest() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(5));
        personList.add(new Person(1));
        personList.add(new Person(2));
        personList.add(new Person(4));
        personList.add(new Person(3));
        personList.add(new Person(5));
        personList = personList
                .stream()
                .collect(collectingAndThen(toCollection(() ->
                        new TreeSet<>((Comparator.comparing(Person::getId)))), ArrayList::new));
        System.out.println(personList);
    }

    /**
     * forEachOrdered是为了保证遍历顺序（并行流），但效率没有forEach高
     */
    @Test
    public void forEachTest() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(i);
        }
        numbers.parallelStream().forEachOrdered(e -> {
            try {
                Thread.sleep(RandomUtil.randomInt(1000));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e);
        });
        System.out.println("==============");
        numbers.parallelStream().forEach(e -> {
            try {
                Thread.sleep(RandomUtil.randomInt(1000));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(e);
        });
    }

    @Test
    public void reduceTest() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            list.add(i);
        }
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
    }

}

@Data
class Person {
    int id;
    String name;
    String sex;
    float height;

    public Person(int id, String name, String sex, float height) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.height = height;
    }

    public Person(int id) {
        this.id = id;
    }
}