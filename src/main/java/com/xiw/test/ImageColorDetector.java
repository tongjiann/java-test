package com.xiw.test;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImageColorDetector {

    public static void main(String[] args) {
        try {
            // 读取图片文件
            File imageFile = new File("/Users/xiwang/Desktop/AC8B709D-6FC6-43E0-B545-4CB0B845093A.jpeg");
            BufferedImage image = ImageIO.read(imageFile);

            // 统计颜色出现的次数
            Map<Integer, Integer> colorCount = new HashMap<>();

            // 遍历图片的像素
            int width = image.getWidth();
            int height = image.getHeight();
            int area = width * height;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // 获取当前像素的颜色
                    Color color = new Color(image.getRGB(x, y));
                    // 计算RGB颜色值的唯一标识
                    int rgb = (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
                    // 统计颜色出现次数
                    colorCount.put(rgb, colorCount.getOrDefault(rgb, 0) + 1);
                }
            }

            // 对颜色出现次数进行排序
            List<Map.Entry<Integer, Integer>> sortedColorCount = new ArrayList<>(colorCount.entrySet());
            sortedColorCount.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // 打印颜色信息
            for (Map.Entry<Integer, Integer> entry : sortedColorCount) {
                // 将RGB值转换为Color对象
                Color color = new Color(entry.getKey());
                // 打印颜色信息和出现次数
                Integer count = entry.getValue();
                double percent = (double) count / area;
                if (count < 500) {
                    continue;
                }
                System.out.println("Color: " + color + ", count:" + count + ",percent: " + percent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
