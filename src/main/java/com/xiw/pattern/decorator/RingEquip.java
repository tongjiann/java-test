package com.xiw.pattern.decorator;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:13
 */
public class RingEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战戒指";
    }

}
