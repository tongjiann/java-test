package com.xiw.pattern.strategy.better;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 14:12
 */
public class DefendTBS implements IDefendBehavior {
    @Override
    public void defend() {
        System.out.println("铁布衫");
    }
}
