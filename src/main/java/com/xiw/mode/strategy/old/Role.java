package com.xiw.mode.strategy.old;

/**
 * @author xiwang
 * @apiNote 角色抽象类
 * @since 2022-05-16 14:01
 */
public abstract class Role {
    protected String name;

    protected abstract void display();

    protected abstract void run();

    protected abstract void attack();

    protected abstract void defend();

}
