package com.rabbit.think.thread.coo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxBuff {
	
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOn(car));
		exec.execute(new BuffOn(car));
		TimeUnit.SECONDS.sleep(10);
		exec.shutdownNow();
	}

}
