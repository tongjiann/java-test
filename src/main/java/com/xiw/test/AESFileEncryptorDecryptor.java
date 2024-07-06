package com.xiw.test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AESFileEncryptorDecryptor {
    private static final Logger logger = Logger.getLogger(AESFileEncryptorDecryptor.class.getName());
    private static final String ALGORITHM = "AES";
    private static final int LOG_INTERVAL_MS = 5000;
    private static final String KEY = "xiwangxiwangxiwa"; // 16字节密钥

    public static void main(String[] args) {
        String folderPath = "/Volumes/H"; // 指定文件夹路径
        List<String> fileExtensionList = Arrays.asList(".mkv", ".mp4", ".avi");

        try {
            // 设置日志记录器
            setupLogger();

            SecretKey secretKey = generateKey(KEY);
            for (String fileExtension : fileExtensionList) {
                logger.info("开始加密文件");
                processFilesInFolder(new File(folderPath), fileExtension, secretKey, Cipher.ENCRYPT_MODE);

//                logger.info("开始解密文件");
//                processFilesInFolder(new File(folderPath), fileExtension + ".encrypted", secretKey, Cipher.DECRYPT_MODE);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "发生异常", e);
        }
    }

    private static void setupLogger() throws IOException {
        FileHandler fileHandler = new FileHandler("file_encryptor_decryptor.log");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
    }

    public static void processFilesInFolder(File folder, String fileExtension, SecretKey secretKey, int cipherMode) {
        if (!folder.isDirectory()) {
            logger.severe("指定的路径不是文件夹: " + folder);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(fileExtension) && !name.startsWith("."));
        if (files == null) {
            logger.severe("无法访问文件夹: " + folder);
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (File file : files) {
            executorService.submit(() -> {
                try {
                    if (cipherMode == Cipher.ENCRYPT_MODE) {
                        encryptFile(file, secretKey);
                    } else {
                        decryptFile(file, secretKey);
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, (cipherMode == Cipher.ENCRYPT_MODE ? "加密" : "解密") + "文件时发生异常: " + file, e);
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // 等待所有任务完成
        }
    }

    public static void encryptFile(File inputFile, SecretKey secretKey) throws Exception {
        File encryptedFile = new File(inputFile.getParent(), inputFile.getName() + ".encrypted");
        processFile(Cipher.ENCRYPT_MODE, inputFile, encryptedFile, secretKey);
    }

    public static void decryptFile(File inputFile, SecretKey secretKey) throws Exception {
        String originalFileName = inputFile.getName().replace(".encrypted", "");
        File decryptedFile = new File(inputFile.getParent(), originalFileName);
        processFile(Cipher.DECRYPT_MODE, inputFile, decryptedFile, secretKey);
    }

    private static void processFile(int cipherMode, File inputFile, File outputFile, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipherMode, secretKey);

        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytes = inputFile.length();
            long processedBytes = 0;
            long startTime = System.currentTimeMillis();
            long lastTime = startTime;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                    processedBytes += bytesRead;

                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTime >= LOG_INTERVAL_MS) {
                        long elapsedTime = currentTime - startTime;
                        long estimatedTotalTime = (totalBytes * elapsedTime) / processedBytes;
                        long remainingTime = estimatedTotalTime - elapsedTime;
                        printProgress(cipherMode == Cipher.ENCRYPT_MODE ? "加密" : "解密", inputFile, processedBytes, totalBytes, remainingTime);
                        lastTime = currentTime;
                    }
                }
            }

            byte[] output = cipher.doFinal();
            if (output != null) {
                outputStream.write(output);
            }

            logger.info((cipherMode == Cipher.ENCRYPT_MODE ? "加密" : "解密") + "完成: " + inputFile);
            inputFile.delete();
            logger.info("删除文件:" + inputFile);
        } catch (IOException e) {
            logger.log(Level.SEVERE, (cipherMode == Cipher.ENCRYPT_MODE ? "加密" : "解密") + "文件时发生异常: " + inputFile, e);
            throw e;
        }
    }

    private static void printProgress(String operation, File file, long processedBytes, long totalBytes, long remainingTime) {
        int progress = (int) ((processedBytes * 100) / totalBytes);
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String humanReadableProcessed = humanReadableByteCount(processedBytes, true);
        String humanReadableTotal = humanReadableByteCount(totalBytes, true);
        String humanReadableRemainingTime = formatTime(remainingTime);
        System.out.printf("[%s] %s进度: %s - %d%%, 已处理: %s, 总大小: %s, 预计剩余时间: %s\n",
                timestamp, operation, file.getName(), progress,
                humanReadableProcessed, humanReadableTotal, humanReadableRemainingTime);
    }

    private static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    private static String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;
        return String.format("%d小时 %d分 %d秒", hours, minutes, seconds);
    }

    private static SecretKey generateKey(String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}