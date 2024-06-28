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

        moveLargeFiles(sourceDir, targetDir);
    }

    private static void moveLargeFiles(File sourceDir, File targetDir) {
        File[] files = sourceDir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                if (file.length() > SIZE_THRESHOLD) {
                    moveFile(file, new File(targetDir, file.getName()));
                }
            } else if (file.isDirectory()) {
                long dirSize = calculateDirectorySize(file);
                if (dirSize > SIZE_THRESHOLD) {
                    moveDirectory(file, new File(targetDir, file.getName()));
                }
            }
        }
    }

    private static void moveFile(File sourceFile, File targetFile) {
        try {
            if (targetFile.exists()) {
                System.out.println("Target file exists. Deleting source file: " + sourceFile.getAbsolutePath());
                Files.delete(sourceFile.toPath());
            } else {
                Files.move(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Moved file: " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error moving file: " + sourceFile.getAbsolutePath() + " to " + targetFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private static void moveDirectory(File sourceDir, File targetDir) {
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File[] files = sourceDir.listFiles();
        if (files == null) return;

        for (File file : files) {
            moveFile(file, new File(targetDir, file.getName()));
        }

        sourceDir.delete(); // Delete the source directory after moving all its contents
    }

    private static long calculateDirectorySize(File dir) {
        long size = 0;
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += calculateDirectorySize(file);
                }
            }
        }
        return size;
    }

}
