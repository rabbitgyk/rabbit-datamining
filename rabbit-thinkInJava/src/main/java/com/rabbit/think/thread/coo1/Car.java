package com.rabbit.think.thread.coo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * using Lock and Condition object
 * @author rabbit
 * @date   Aug 3, 2014
 */

public class Car {
	private Lock lock = new ReentrantLock();
	private Condition conditon = lock.newCondition();
	private boolean waxOn = false; //是否涂蜡
	//涂完了蜡
	public void waxed(){
		lock.lock();
		try {
			waxOn = true; //Read to buff
			conditon.signalAll();
		} finally {
			lock.unlock();
		}
	}
	//抛完了光
	public void buffed(){
		lock.lock();
		try {
			waxOn = false;
			conditon.signalAll();
		} finally {
			lock.unlock();
		}
	}
	//等待涂蜡
	public void waitForWaxing() throws InterruptedException{
		lock.lock();
		try {
			while (waxOn == false)
				conditon.await();
		} finally {
			lock.unlock();
		}
	}
	//等待抛光
	public void waitForBuffing() throws InterruptedException{
		lock.lock();
		try {
			while (waxOn == true)
				conditon.await();
		} finally {
			lock.unlock();
		}
	}
}
