package com.xiw.pattern.status;

import com.xiw.pattern.status.old.VendingMachine;
import org.junit.Test;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:47
 */
public class StatusTest {
    @Test
    public void statusTest(){
        VendingMachine machine = new VendingMachine(10);
		machine.insertMoney();
		machine.backMoney();

		System.out.println("-----------");

		machine.insertMoney();
		machine.turnCrank();

		System.out.println("----------压力测试-----");
		machine.insertMoney();
		machine.insertMoney();
		machine.turnCrank();
		machine.turnCrank();
		machine.backMoney();
		machine.turnCrank();

    }
}
