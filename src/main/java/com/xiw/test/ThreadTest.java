package com.xiw.test;

import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadTest {

    // public static final String TARGET_MD5 = "7c7dee7071ba9f4aaf0a49236a12df09";
    public static final String TARGET_MD5 = "b66f54be65198152811a0d87f1ef68a5";

    private final AtomicReference<Integer> finished = new AtomicReference<>(0);

    private boolean flag = false;

    public static String bytes2String(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Test
    public void ThreadTest() {
        int THREAD_COUNT = 99;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        // 启动多个线程执行任务
        for (int i = 10; i < THREAD_COUNT + 1; i++) {
            Runnable worker = new WorkerThread(new BigInteger("1" + i + "00000000"), 100000000, i);
            executor.execute(worker);
        }
        while (!flag || !finished.get().equals(THREAD_COUNT)) {
            // 关闭线程池
        }
        executor.shutdown();
        System.out.println("总任务结束");


    }

    @Test
    public void test() {
        String s = "_kyser";
        MD5 md5 = MD5.create();
        byte[] digest = md5.digest(s);
        String res = bytes2String(digest);
        System.out.println(res);
    }

    class WorkerThread implements Runnable {

        private final int cycleTimes;

        private final int taskIndex;

        private BigInteger startInt;

        public WorkerThread(BigInteger startInt, int cycleTimes, int taskIndex) {
            this.startInt = startInt;
            this.cycleTimes = cycleTimes;
            this.taskIndex = taskIndex;
        }

        @Override
        public void run() {
            final MD5 md5 = MD5.create();

            // boolean flag = false;
            int cnt = 0;
            long start = System.currentTimeMillis();
            long end;
            System.out.println(taskIndex + " 任务启动，开始数字为"+startInt);

            for (int i = 0; i < cycleTimes && !flag; i++) {

                // System.out.println(phoneNumber);
                byte[] digest = md5.digest(startInt.toString());
                String res = bytes2String(digest);
                if (TARGET_MD5.contentEquals(res)) {
                    System.out.println("=================");
                    end = System.currentTimeMillis();
                    System.out.println("尝试了" + cnt + "次，耗时" + (end - start) + "ms");
                    System.out.println(startInt);
                    System.out.println("=================");
                    flag = true;
                }
                startInt = startInt.add(BigInteger.ONE);
                cnt++;
                if (cnt % 1000000 == 0) {
                    end = System.currentTimeMillis();
                    System.out.println(taskIndex + " 尝试了" + cnt + "次，耗时" + (end - start) + "ms，最后一次数字为" + startInt.subtract(BigInteger.ONE));
                }
            }
            // startInt = startInt.add(new BigInteger("10000000000"));
            synchronized (finished) {
                finished.set(finished.get() + 1);
                System.out.println(taskIndex + " 任务结束，" +
                        (startInt.subtract(new BigInteger(String.valueOf(cycleTimes)))) + "开始｜" +
                        (startInt.subtract(BigInteger.ONE)) + "结束");
            }
        }


    }


}

