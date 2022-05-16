package com.xiw.mode.singleton;

/**
 * @apiNote 枚举，非常推荐
 * @author xiwang
 * @since 2022-05-16 11:42
 */
public enum SingletonEnum {

	 instance;

	 private SingletonEnum() {}

	 public Object method(){
		 return new Object();
	 }
}