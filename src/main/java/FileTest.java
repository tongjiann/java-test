import cn.hutool.core.io.FileUtil;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File file = FileUtil.file("D:\\wallpaper\\20211104_FoleysBridge_ZH-CN4338959688.jpg");
        System.out.println(file);
    }
}
