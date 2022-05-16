package com.xiw.pattern.strategy.old;

/**
 * @author xiwang
 * @apiNote 角色A
 * @since 2022-05-16 14:02
 */
public class RoleA extends Role {
    public RoleA(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        System.out.println("样子1");
    }

    @Override
    protected void run() {
        System.out.println("金蝉脱壳");
    }

    @Override
    protected void attack() {
        System.out.println("降龙十八掌");
    }

    @Override
    protected void defend() {
        System.out.println("铁头功");
    }

}
