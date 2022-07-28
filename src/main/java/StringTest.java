import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author jianweitong
 */
public class StringTest {
    public final static String DOT = ".";
    public final static String SIGNED_SUFFIX_STR = "（已签署）";

    public static void main(String[] args) throws Exception {
//        slash("\\Users\\jianweitong\\Documents\\dir\\ric\\attachment_root\\78\\787C26DA-1DAF-42A8-BDDB-078B2C446EB8\\桐庐电子签章(1).pdf");

//        dot("13.41.pdf");

//        contain(Collections.singletonList("null"),null);

        readBinFile("/Users/jianweitong/Downloads/Untitled");
    }

    private static void dot(String oldFileName) {
        String fileName;
        if (oldFileName.contains(DOT)) {
            fileName = oldFileName.substring(0, oldFileName.lastIndexOf(DOT))
                    + SIGNED_SUFFIX_STR
                    + oldFileName.substring(oldFileName.lastIndexOf(DOT));
        } else {
            fileName = oldFileName;
        }
        System.out.println(fileName);
    }


    /*
     * split中使用的是正则表达式，所以要用\\\\而不是\\来匹配字符串中的\\
     */
    private static void slash(String str) {
        String SLASH = "/";
        String OPPOSITE_SLASH = "\\\\";
        String OPPOSITE_SLASH2 = "\\";

        System.out.println(str + "包含//:" + str.contains(SLASH));
        System.out.println(str + "包含\\:" + str.contains(OPPOSITE_SLASH2));
        System.out.println(str + "包含\\\\:" + str.contains(OPPOSITE_SLASH));
        System.out.println("==============================================");
        System.out.println("使用//切割后的最后一个字符:" + str.split(SLASH)[str.split(SLASH).length - 1]);
        System.out.println("使用\\\\切割后的最后一个字符:" + str.split(OPPOSITE_SLASH)[str.split(OPPOSITE_SLASH).length - 1]);
//        System.out.println("使用\\切割后的最后一个字符:" + str.split(OPPOSITE_SLASH2)[str.split(OPPOSITE_SLASH2).length-1]);
    }

    private static <T> void contain(List<T> list, T object) throws Exception {
        if (list == null || list.size() == 0) {
            throw new Exception("数组为空");
        }
        System.out.println(list.contains(object));
    }

    private static void readBinFile(String filePath) throws IOException {
        DataInputStream dis = null;

        dis = new DataInputStream(new FileInputStream(filePath));

        byte[] b = new byte[1024];

        dis.read(b);
    }

    @Test
    public void nullTest(){
        String a = null;
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(a));
        System.out.println(bigDecimal);
    }

    @Test
    public void splitTest(){
        String str = "com.yaagoole.ric.service.ru.BudgetAllocateFormServiceImpl$$EnhancerBySpringCGLIB$$a1c72625";
        System.out.println(Arrays.toString(str.split("\\$")));
        System.out.println(111_222_333);
    }

    @Test
    public void replaceTest(){
        String str = "";
        String replace = str.replace("\n", "、");
        System.out.println(replace);
    }


}
