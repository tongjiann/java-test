import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("1", "2");
        System.out.println(strings.toString());
    }

    @Test
    public void SubListTest(){
        List<String > list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("1");
        }
        System.out.println(list.subList(1,10));
    }
}
