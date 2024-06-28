package com.xiw.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileCopyTest {

    private static final long SIZE_THRESHOLD = 500L * 1024 * 1024; // 500MB in bytes

    public static void main(String[] args) {
        File sourceDir = new File("path/to/A");
        File targetDir = new File("path/to/B");

        if (!sourceDir.exists() || !sourceDir.isDirectory()) {
            System.out.println("Source directory does not exist or is not a directory.");
            return;
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        copyLargeFiles(sourceDir, targetDir);
    }

    private static void copyLargeFiles(File sourceDir, File targetDir) {
        File[] files = sourceDir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                if (file.length() > SIZE_THRESHOLD) {
                    copyFile(file, new File(targetDir, file.getName()));
                }
            } else if (file.isDirectory()) {
                // 递归遍历子目录
                copyLargeFiles(file, targetDir);
            }
        }
    }

    private static void copyFile(File sourceFile, File targetFile) {
        try {
            if (targetFile.exists()) {
                System.out.println("Target file exists. Deleting source file: " + sourceFile.getAbsolutePath());
                Files.delete(sourceFile.toPath());
            } else {
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied file: " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
                Files.delete(sourceFile.toPath());
            }
        } catch (IOException e) {
            System.err.println("Error copying file: " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
