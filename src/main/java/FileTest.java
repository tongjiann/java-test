import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileTest {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\wallpaper\\20211104_FoleysBridge_ZH-CN4338959688.jpg");
        System.out.println(file);
    }

    @Test
    public void test(){
        File file = new File("/Users/xiwang/Desktop/server(1).log");
        String s = FileUtil.readString(file, Charset.defaultCharset());
        String[] split = s.split("\n");
        List<String> collect = Arrays.stream(split).filter(e -> e.contains(".ric.")).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }
}
