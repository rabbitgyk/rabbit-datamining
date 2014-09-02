package com.rabbit.think.thread;

public class Accessor implements Runnable{

	private final int id;
	public Accessor(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
		}
	}
	
	@Override
	public String toString() {
		return "##" + id + " : " + ThreadLocalVariableHolder.get();
	}
}
