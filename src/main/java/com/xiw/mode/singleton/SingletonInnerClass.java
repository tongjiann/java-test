package com.xiw.mode.singleton;

/**
 * @author xiwang
 * @apiNote 内部类 推荐
 * @since 2022-05-16 11:38
 */
public class SingletonInnerClass {
    private SingletonInnerClass() {};

	private static class SingletonHolder{
		private static final SingletonInnerClass instance=new SingletonInnerClass();
	}

	public static SingletonInnerClass getInstance(){
		return SingletonHolder.instance;
	}
}
