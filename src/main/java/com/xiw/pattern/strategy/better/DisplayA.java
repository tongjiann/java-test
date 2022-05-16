package com.xiw.pattern.strategy.better;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 14:15
 */
public class DisplayA implements IDisplayBehavior{
    @Override
    public void display() {
        System.out.println("样式A");
    }
}
