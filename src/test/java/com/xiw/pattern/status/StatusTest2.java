package com.xiw.pattern.status;

import com.xiw.pattern.status.better.VendingMachine;
import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:47
 */
public class StatusTest2 {
    @Test
    public void statusTest(){
        VendingMachine machine = new VendingMachine(10);
		machine.insertMoney();
		machine.backMoney();

		System.out.println("----我要中奖----");

		machine.insertMoney();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();

		System.out.println("-------压力测试------");

		machine.insertMoney();
		machine.backMoney();
		machine.backMoney();
		machine.turnCrank();// 无效操作
		machine.turnCrank();// 无效操作
		machine.backMoney();

    }
}
