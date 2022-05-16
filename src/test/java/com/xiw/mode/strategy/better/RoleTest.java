package com.xiw.mode.strategy.better;


import org.junit.Test;

public class RoleTest {

    @Test
    public void betterRoleTest() {
        Role roleA = new Role("角色A");
        roleA.setAttackBehavior(new AttackJYSG())
                .setDisplayBehavior(new DisplayA())
                .setRunBehavior(new RunJCTQ())
                .setDefendBehavior(new DefendTBS());
        System.out.println(roleA.name + ":");
        roleA.run();
        roleA.attack();
        roleA.defend();
        roleA.display();

    }

}