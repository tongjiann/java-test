package com.xiw.pattern.template;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-05-16 15:37
 */
public class TestWorker extends Worker {
    public TestWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
		System.out.println(name + "写测试用例-提交bug-写测试用例");
    }
}
