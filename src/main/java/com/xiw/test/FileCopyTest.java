package com.xiw.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileCopyTest {

    private static final long SIZE_THRESHOLD = 500L * 1024 * 1024; // 500MB in bytes

    public static void main(String[] args) throws IOException {
//        File sourceDir = new File("/Volumes/爱国者/HHHHH/Video");
        File sourceDir = new File("/Volumes/H");
        File targetDir = new File("/Volumes/H");

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("Source directory does not exist or is not a directory.");
            return;
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        copyLargeFiles(sourceDir, targetDir);
    }

    private static void copyLargeFiles(File sourceDir, File targetDir) throws IOException {
        File[] files = sourceDir.listFiles();
        if (files == null) return;
        List<String> ignoreFileList = Arrays.asList(".Spotlight-V100","WATCHED",".Trashes");

        List<File> fileList = Arrays.stream(files).filter(e -> {
            for (String s : ignoreFileList) {
                if (e.getAbsolutePath().contains(s)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        for (File file : fileList) {
            if (file.isFile()) {
                if (file.length() > SIZE_THRESHOLD) {
                    copyFile(file, new File(targetDir, file.getName()));
                }
                if (!file.getParentFile().toString().equals(targetDir.toPath().toString())) {
                    System.out.println("delete file" + file.getAbsolutePath());
                    Files.delete(file.toPath());
                }
            } else if (file.isDirectory()) {
                // 递归遍历子目录
                copyLargeFiles(file, targetDir);
                System.out.println("delete dir" + file.getAbsolutePath());
                Files.delete(file.toPath());
            } else {
                System.out.println("delete unknown type file" + file.getAbsolutePath());
                Files.delete(file.toPath());
            }
        }
    }

    private static void copyFile(File sourceFile, File targetFile) {
        try {
            if (targetFile.exists()) {
                System.out.println("Target file exists. : " + sourceFile.getAbsolutePath());
            } else {
                System.out.println("Copied file: " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
