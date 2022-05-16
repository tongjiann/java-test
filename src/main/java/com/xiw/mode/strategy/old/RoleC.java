package com.xiw.mode.strategy.old;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 14:05
 */
public class RoleC extends Role {
    public RoleC(String name) {
        this.name = name;
    }

    @Override
    protected void display() {
        //从RoleA中拷贝
        System.out.println("样子1");
    }

    @Override
    protected void run() {
        System.out.println("烟雾弹");
    }

    @Override
    protected void attack() {
        System.out.println("九阳神功");
    }

    @Override
    protected void defend() {
        //从B中拷贝
        System.out.println("铁布衫");
    }

}
