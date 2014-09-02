package com.rabbit.think.thread.coo;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable{

	private Car car;
	public WaxOn(Car car){
		this.car = car;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				System.out.println("waxing ......");
				TimeUnit.SECONDS.sleep(1);
				car.waxed();
				car.waitForBuffing();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("wax on task is end!");
	}

}
