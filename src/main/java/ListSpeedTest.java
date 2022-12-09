import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tzq
 * @date 2019-03-13 16:48
 */

public class ListSpeedTest {
    //公共变量
    int count = 0;

    static Class<?> LIST_CLASS = ArrayList.class;

    static final int LIST_SIZE = 1000000;

    static final int CYCLE_NUMBER = 20;

    public static void main(String[] args) throws Exception {

        List<Long> extendForTimeList = new ArrayList<>();
        List<Long> iterTimeList = new ArrayList<>();
        List<Long> stdTimeList = new ArrayList<>();
        List<Long> stdWithSizeTimeList = new ArrayList<>();
        List<Long> streamTimeList = new ArrayList<>();
        for (int i = 0; i < CYCLE_NUMBER; i++) {
            System.out.println("当前正在进行第" + i + "遍循环");
            List<String> list = (List<String>) LIST_CLASS.getDeclaredConstructor().newInstance();

            fillList(list);
            extendForTimeList.add(extendFor(list));
            iterTimeList.add(iter(list));

//            stdTimeList.add(std(list));

//            stdWithSizeTimeList.add(stdWithSize(list));

            streamTimeList.add(stream(list));
        }

        System.out.println("当前列表类型" + LIST_CLASS);
        System.out.println("当前列表大小" + LIST_SIZE);
        System.out.println("当前循环数量" + CYCLE_NUMBER);

        printRes("增强for循环\t\t\t", extendForTimeList);
        printRes("迭代\t\t\t\t\t", iterTimeList);
//        printRes("标准for循环（无size）\t", stdTimeList);
//        printRes("标准for循环（带size）\t", stdWithSizeTimeList);
        printRes("stream\t\t\t\t", streamTimeList);


    }

    private static void printRes(String name, List<Long> extendForTimeList) {
        System.out.println(name + "｜"
                + "平均时间" + extendForTimeList.stream().collect(Collectors.averagingLong(Long::valueOf))
                + "|最长时间" + extendForTimeList.stream().max(Long::compareTo).get()
                + "|最短时间" + extendForTimeList.stream().min(Long::compareTo).get()
                + "|所有时间" + extendForTimeList
        );
    }

    private static void fillList(List<String> list) {
        list.clear();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(UUID.randomUUID().toString());
        }
    }

    private static long stream(List<String> list) {
        long s5 = System.nanoTime();
        list.stream().forEach(x -> {
            Object o = x;
        });
        return (System.nanoTime() - s5);
    }

    private static long stdWithSize(List<String> list) {
        long s4 = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
        }
        return (System.nanoTime() - s4);
    }

    private static long std(List<String> list) {
        long s3 = System.nanoTime();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object o = list.get(i);
        }
        return (System.nanoTime() - s3);
    }

    private static long iter(List<String> list) {
        long s2 = System.nanoTime();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Object o = iter.next();
        }
        return (System.nanoTime() - s2);
    }

    private static long extendFor(List<String> list) {
        long s1 = System.nanoTime();
        for (String i : list) {
            Object o = i;
        }
        return (System.nanoTime() - s1);
    }
}
