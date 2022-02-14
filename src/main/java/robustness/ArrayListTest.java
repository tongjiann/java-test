package robustness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListTest {
    static final String SPLIT = "==================================\n";

    public static void main(String[] args) {
        subListTest();
        addAllTest();
    }

    /**
     * addAll的入参不允许为null
     * <p>在{@link ArrayList#addAll}方法的第一行代码即Object[] a = c.toArray()；其中c为输入集合参数，如果为null，则直接抛出异常。</p>
     */
    private static void addAllTest() {
        System.out.println(SPLIT + "addAllTest");
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        List<String> list2 = null;
        try {
            list.addAll(list2);
        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        System.out.println(list);
    }

    /**
     * <p>{@link ArrayList#subList(int, int)}的返回值是ArrayList的内部类SubList，不能强转为ArrayList</p>
     * <p>而且当父集合进行元素的增加或者修改，均会导致子集合遍历、增加、修改产生ConcurrentModificationException异常</p>
     * <p>子集合添加、删除元素均会影响父集合</p>
     * <p>{@link ArrayList#subList(int, int)}返回的是ArrayList的内部类SubList，并不是{@link ArrayList}本身，而是ArrayList的一个视图</p>
     * <p>对于SubList的所有操作最终会反映到原列表上。列表改动均会引起checkForComodification异常</p>
     */
    private static void subListTest() {
        System.out.println(SPLIT + "subListTest");
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        List<String> subList = list.subList(0, 2);
        System.out.println(subList);
        subList.add("1");
        System.out.println(subList);
        System.out.println(list);
        subList.remove(2);
        System.out.println(subList);
        System.out.println(list);
        list.add("4");
        try {
            System.out.println(subList);
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


}
