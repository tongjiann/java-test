package com.xiw.photo.tool;

import cn.hutool.core.io.FileUtil;
import com.xiw.photo.constant.Constant;
import com.xiw.photo.exception.SelfException;
import com.xiw.photo.util.SelfUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

/**
 * 图片整理初始化工具类
 */
public class PhotoArrangeInitTool {

    public static void main(String[] args) {

        var toPickDir = new File(Constant.WORK_DIR + File.separator + Constant.TO_PICK_DIR_NAME);

        buildAndMove(toPickDir, Constant.CR3_DIR_NAME, Constant.CR3_SUFFIX);

        buildAndMove(toPickDir, Constant.MOVIE_DIR_NAME, Constant.MP4_SUFFIX);

        buildSonDir(toPickDir);

    }

    private static void buildAndMove(File sourceDir, String targetDirName, String suffix) {
        File targetDir = buildDir(targetDirName);
        moveFile(sourceDir, targetDir, suffix);
    }

    private static void moveFile(File toPickDir, File targetDir, String suffix) {
        if (StringUtils.isBlank(suffix) || !StringUtils.startsWith(suffix, ".")) {
            throw new SelfException("非法的后缀名【" + suffix + "】");
        }
        SelfUtil.checkAndGetDir(toPickDir);
        SelfUtil.checkAndGetDir(targetDir);
        File[] files = toPickDir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("【" + toPickDir + "】文件夹内的文件数为空，跳过");
            return;
        }
        Arrays.stream(files).filter(e -> e.getName().toLowerCase(Locale.ROOT).endsWith(suffix))
              .forEach(f -> {
                  FileUtil.move(f, targetDir, true);
                  System.out.println("移动文件【" + f + "】");
              });
    }

    private static void buildSonDir(File parentDir) {
        if (!FileUtil.exist(parentDir) || !FileUtil.isDirectory(parentDir)) {
            throw new SelfException("【" + parentDir + "】不存在或不是一个目录");
        }
        for (String dirPath : Constant.TO_CREATE_DIRECTORY_NAME_LIST) {
            var file = new File(parentDir.getPath() + File.separator + dirPath);
            if (FileUtil.exist(file) || FileUtil.isDirectory(file)) {
                System.err.println("【" + parentDir + "】已存在【" + file.getName() + "】");
                continue;
            }
            SelfUtil.mkdir(file);
            if (file.exists()) {
                System.out.println("成功创建文件夹【" + file.getName() + "】");
            }
        }
    }

    /**
     * 创建当前项目文件夹
     */
    private static File buildDir(String dirName) {
        if (StringUtils.isBlank(dirName)) {
            throw new SelfException("dirName非法");
        }
        File workDir = SelfUtil.getWorkDir();
        System.out.println("当前工作区【" + workDir + "】");
        String[] existList = workDir.list();
        var file = new File(workDir.getAbsolutePath() + File.separator + dirName);
        if (existList != null && Arrays.asList(existList).contains(dirName)) {
            System.out.println("当前工作区下已存在【" + dirName + "】，跳过创建");
            return file;
        }
        SelfUtil.mkdir(file);
        System.out.println("在当前工作区创建【" + dirName + "】");
        return file;
    }

}
