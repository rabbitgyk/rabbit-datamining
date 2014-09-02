package com.rabbit.think.thread;

public abstract class IntGenerator {
	/**
	 * 是否停止生成整数
	 */
	private volatile boolean canceled = false;
	/**
	 * 获取先一个整数
	 * @return
	 */
	public abstract int next();
	
	public abstract void add1();
	
	public void cancel(){
		this.canceled = true;
	}
	
	public boolean isCanceled(){
		return this.canceled;
	}

}
