package com.rabbit.think.thread.coo1;

import java.util.concurrent.TimeUnit;

public class BuffOn implements Runnable{
	private Car car;
	public BuffOn(Car car){
		this.car = car;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				car.waitForWaxing();
				System.out.println("buffing ......");
				TimeUnit.SECONDS.sleep(1);
				car.buffed();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("buff task is end!");
	}
}
