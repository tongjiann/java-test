package com.xiw.test;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RedColorDetector {

    public static void main(String[] args) {
        try {
            // 读取图片文件
            File imageFile = new File("/Users/xiwang/Desktop/AC8B709D-6FC6-43E0-B545-4CB0B845093A.jpeg");
            BufferedImage image = ImageIO.read(imageFile);

            // 判断是否有红色
            boolean hasRed = false;

            // 遍历图片的像素
            int width = image.getWidth();
            int height = image.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // 获取当前像素的颜色
                    Color color = new Color(image.getRGB(x, y));
                    // 判断颜色是否为红色
                    if (color.getRed() > 100 && color.getGreen() < 50 && color.getBlue() < 50) {
                        hasRed = true;
                        break;
                    }
                }
                if (hasRed) {
                    break;
                }
            }

            // 输出结果
            if (hasRed) {
                System.out.println("图片中有红色");
            } else {
                System.out.println("图片中没有红色");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
