import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTest {
    public static void main(String[] args) {
        List<String > list = Arrays.asList("1","2");
        List<Map<String, String>> collect = list.parallelStream().map(streamTest::string2Map).collect(Collectors.toList());
        System.out.println(collect);
    }
    public static Map<String ,String > string2Map(String  string){
        Map<String , String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("key",string);
        return objectObjectHashMap;
    }

    public static void main05(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        System.out.println("test"+list.stream().map(e->e+1).collect(Collectors.toList()));
        System.out.println("test"+list);
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
}
