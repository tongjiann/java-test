package com.xiw.mode.observer.example;

import java.util.Locale;
import java.util.UUID;

/**
 * @author xiwang
 * @since 2022-05-13 14:27
 */
public class ObserverModeTest {
    public static void main(String[] args) {
        // 创建一个消息发布者
        SubjectFor3D subjectFor3D = new SubjectFor3D();
        // 添加两个消息的接收者
        RealObserver realObserver = new RealObserver("张三", subjectFor3D);
        RealObserver realObserver2 = new RealObserver("李四", subjectFor3D);
        // 发布者发布一条消息
        subjectFor3D.setMessageFor3D(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
        // 接收者1取消接收信息
        subjectFor3D.removeObserver(realObserver);
        // 发布者发布一条消息
        subjectFor3D.setMessageFor3D(UUID.randomUUID().toString().toUpperCase(Locale.ROOT));
    }
}
