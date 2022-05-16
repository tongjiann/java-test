package com.xiw.pattern.strategy.better;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 14:12
 */
public class AttackJYSG implements IAttackBehavior {
    @Override
    public void attack() {
        System.out.println("九阳神功！");
    }
}
