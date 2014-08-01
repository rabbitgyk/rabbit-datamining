package com.rabbit.think.thread.coo;

public class Car {

	private boolean waxOn = false; //是否涂蜡
	//涂完了蜡
	public synchronized void waxed(){
		waxOn = true;
		notifyAll();
	}
	//抛完了光
	public synchronized void buffed(){
		waxOn = false;
		notifyAll();
	}
	//等待涂蜡
	public synchronized void waitForWaxing() throws InterruptedException{
		while(waxOn == false){
			wait();
//			System.out.println("wait wait wait ...");
		}
			
	}
	//等待抛光
	public synchronized void waitForBuffing() throws InterruptedException{
		while(waxOn == true)
			wait();
	}
}
