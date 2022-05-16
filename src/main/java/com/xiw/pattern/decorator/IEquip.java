package com.xiw.pattern.decorator;

/**
 * @author xiwang
 * @since 2022-05-16 15:09
 */
public interface IEquip {
    /**
     * 计算攻击力
     *
     * @return
     */
    int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    String description();
}
