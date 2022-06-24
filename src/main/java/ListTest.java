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
    public void SubListTest() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("1");
        }
        System.out.println(list.subList(1, 10));
    }

    @Test
    public void testRemove() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(String.valueOf(i));
        }
        boolean remove = list.remove(null);
        System.out.println(remove);
        System.out.println(list);
    }

    @Test
    public void testRemove2() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            list.add(String.valueOf(i));
        }
        while (list.size() > 10) {
            System.out.println(list.subList(0, 10));
            list.removeIf(e -> list.indexOf(e) < 10);
        }
        System.out.println(list);
    }

    @Test
    public void testContainNull() {
        ArrayList arrayList = new ArrayList();
        System.out.println(arrayList.contains(null));
        arrayList.add("1");
        System.out.println(arrayList.contains(null));

    }

    @Test
    public void testLastIndexOf(){
        List<Integer> list = new ArrayList<>();
        System.out.println(list.lastIndexOf(1));

    }

    @Test
    public void testAddNull(){
        List<Integer> list = new ArrayList<>();
        list.add(null);
        list.add(1);
        System.out.println(list);
    }
}