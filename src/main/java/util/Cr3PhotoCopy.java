package util;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cr3PhotoCopy {

    public static final String SOURCE_DIR = "/Users/xiwang/Documents/相册/待筛选/自己";
    public static final String TARGET_DIR = "/Users/xiwang/Documents/相册/待筛选/CR3";

    private static String getSourceDir() {
        return SOURCE_DIR;
    }

    private static String getDesDir() {
        return TARGET_DIR;
    }

    public static void main(String[] args) {
        copy(getSourceDir(), getDesDir());
    }

    private static void copy(String sourceDir, String targetDir) {
        File sourcePath = checkAndGetDir(sourceDir);
        File targetPath = checkAndGetDir(targetDir);
        if (sourceDir == null || targetDir == null) {
            System.out.println("目录异常，请检查");
            return;
        }
        assert sourcePath != null;
        File[] fileList = sourcePath.listFiles();
        assert targetPath != null;
        File[] targetFileList = targetPath.listFiles();
        if (targetFileList == null || targetFileList.length == 0) {
            System.out.println("目标文件夹不存在文件，结束");
            return;
        }
        var successCnt = 0;
        var failCnt = 0;
        Map<String, File> nameFileMap = Arrays.stream(targetFileList)
                                              .collect(Collectors.toMap(e -> e.getName()
                                                                              .toLowerCase(Locale.ROOT),
                                                      Function.identity()));

        List<String> cr3List = Arrays.stream(fileList)
                                     .map(File::getName)
                                     .map(e -> e.toLowerCase(Locale.ROOT))
                                     .filter(e -> e.endsWith(".cr3"))
                                     .collect(Collectors.toList());
        for (File file : fileList) {
            String fileName = file.getName().toLowerCase(Locale.ROOT);
            if (!fileName.endsWith(".jpg")) {
                System.out.println(fileName + "不是以.jpg结尾，跳过寻找对应的CR3文件");
                failCnt++;
                continue;
            }
            String cr3FileName = fileName.replace("jpg", "cr3");
            if (cr3List.contains(cr3FileName)) {
                System.out.println("当前文件夹存在同名的CR3文件，跳过");
                failCnt++;
                continue;
            }
            File cr3File = nameFileMap.get(cr3FileName);
            if (cr3File == null) {
                System.out.println("未找到" + cr3FileName + "文件，跳过");
                failCnt++;
                continue;
            }
            FileUtil.copy(cr3File, sourcePath, true);
            successCnt++;
        }
        System.out.println("本次复制结束，共成功" + successCnt + "张，失败" + failCnt + "张");
    }

    private static File checkAndGetDir(String dir) {
        if (StringUtils.isBlank(dir) || !FileUtil.isDirectory(dir)) {
            return null;
        }
        return new File(dir);
    }
}
