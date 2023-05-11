package com.xiw.photo.tool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.xiw.photo.constant.Constant.*;
import static com.xiw.photo.util.SelfUtil.checkAndGetDir;
import static com.xiw.photo.util.SelfUtil.getWorkDir;

public class PhotoArrangeFinishTool {
    public static void main(String[] args) {
        System.out.println("工作区【" + getWorkDir() + "】");
        // 复制CR3文件
        copy(getJpgDir() + File.separator + PICKED_DIR_NAME, getCr3Dir());
        System.out.println("CR3文件复制结束");
        // 循环删除空文件夹
        deleteEmptyDir(getJpgDir());
        System.out.println("空文件夹删除结束");

        // 修改父级文件夹名字
        updateParentDirName();
    }

    private static void updateParentDirName() {
        String jpgDir = getJpgDir();
        File file = checkAndGetDir(jpgDir);
        String newFileName = getWorkDir() + File.separator + CURRENT_PHOTO_PROJECT_NAME;
        FileUtil.rename(file, newFileName, false, true);
    }

    private static void deleteEmptyDir(String jpgDir) {
        var file = checkAndGetDir(jpgDir);
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("【" + jpgDir + "】路径下文件数量为空，删除空文件夹");
            FileUtil.del(file);
            return;
        }
        for (File tmpFile : files) {
            if (tmpFile.isDirectory()) {
                deleteEmptyDir(tmpFile.getAbsolutePath());
            }
        }
    }


    private static String getJpgDir() {
        return WORK_DIR + File.separator + TO_PICK_DIR_NAME;
    }

    private static String getCr3Dir() {
        return WORK_DIR + File.separator + CR3_DIR_NAME;
    }

    /**
     * 找出jpgDir目录下的jpg文件在cr3Dir目录下的cr3文件并复制到jpgDir下
     */
    public static void copy(String jpgDir, String cr3Dir) {
        if (jpgDir == null || cr3Dir == null) {
            System.out.println("目录异常，请检查");
            return;
        }
        File jpgPath = checkAndGetDir(jpgDir);
        File cr3Path = checkAndGetDir(cr3Dir);
        File[] targetFileList = cr3Path.listFiles();
        if (targetFileList == null || targetFileList.length == 0) {
            System.out.println("目标文件夹不存在文件，结束");
            return;
        }


        var fileList = jpgPath.listFiles();
        if (fileList == null || fileList.length == 0) {
            System.err.println("jpg文件夹为空");
            return;
        }

        var successCnt = 0;
        var failCnt = 0;
        for (var file : fileList) {
            var fileName = file.getName().toLowerCase(Locale.ROOT);
            if (!fileName.endsWith(JPG_SUFFIX)) {
                System.out.println(fileName + "不是以.jpg结尾，跳过寻找对应的CR3文件");
                failCnt++;
                continue;
            }
            var cr3FileName = fileName.replace(JPG_SUFFIX, CR3_SUFFIX);
            List<String> cr3List = Arrays.stream(fileList)
                                         .map(File::getName)
                                         .map(e -> e.toLowerCase(Locale.ROOT))
                                         .filter(e -> e.endsWith(CR3_SUFFIX))
                                         .collect(Collectors.toList());
            if (cr3List.contains(cr3FileName)) {
                System.out.println("当前文件夹存在同名的CR3文件，跳过");
                failCnt++;
                continue;
            }
            Map<String, File> nameFileMap = Arrays.stream(targetFileList)
                                                  .collect(Collectors.toMap(e -> e.getName()
                                                                                  .toLowerCase(Locale.ROOT),
                                                          Function.identity()));
            var cr3File = nameFileMap.get(cr3FileName);
            if (cr3File == null) {
                System.out.println("未找到" + cr3FileName + "文件，跳过");
                failCnt++;
                continue;
            }
            FileUtil.copy(cr3File, jpgPath, true);
            successCnt++;
        }
        System.out.println("本次复制结束，共成功" + successCnt + "张，失败" + failCnt + "张");
    }

}
