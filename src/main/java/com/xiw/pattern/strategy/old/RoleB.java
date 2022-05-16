package com.xiw.pattern.strategy.old;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 14:04
 */
public class RoleB extends Role {

    public RoleB(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        System.out.println("样子2");
    }

    @Override
    protected void run() {
        //从RoleA中拷贝
        System.out.println("金蝉脱壳");
    }

    @Override
    protected void attack() {
        //从RoleA中拷贝
        System.out.println("降龙十八掌");
    }

    @Override
    protected void defend() {
        System.out.println("铁布衫");
    }


}
