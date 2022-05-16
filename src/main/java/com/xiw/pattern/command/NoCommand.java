package com.xiw.pattern.command;

/**
 * Created by jingbin on 2016/10/31.
 * 避免=null的情况
 */

public class NoCommand implements Command {

    @Override
    public void execute() {
        System.out.println("暂时没有对应的命令");
    }
}
