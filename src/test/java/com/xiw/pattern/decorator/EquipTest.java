package com.xiw.pattern.decorator;

import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:18
 */
public class EquipTest {

    @Test
    public void test(){
        IEquip equip = new RedGemDecorator(new RedGemDecorator(new RedGemDecorator(new ArmEquip())));
		System.out.println("攻击力  :  " + equip.caculateAttack());
		System.out.println("描述 : " + equip.description());    }
}
