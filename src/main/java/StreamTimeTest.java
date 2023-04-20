import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamTimeTest {
    @Test
    public void timeTest() {

        Integer circleTimes = Integer.MAX_VALUE;
        List<Integer> smallList = new ArrayList<>(100);
        for (int i = 0; i<100; i++) {
            smallList.add(i);
        }

        List<Integer> middleList = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            middleList.add(i);
        }
        List<Integer> bigList = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            bigList.add(i);
        }
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < circleTimes; i++) {
            method1(smallList);
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < circleTimes; i++) {
            method1(middleList);
        }
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);


        long start3 = System.currentTimeMillis();
        for (int i = 0; i < circleTimes; i++) {
            method1(bigList);
        }
        long end3 = System.currentTimeMillis();
        System.out.println(end3 - start3);
    }

    public void method1(List<Integer> objectList) {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum+=i;
        }
    }

}
