package com.xiw.pattern.decorator;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:12
 */
public class ArmEquip implements IEquip {
    @Override
    public int caculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "屠龙刀";
    }

}
