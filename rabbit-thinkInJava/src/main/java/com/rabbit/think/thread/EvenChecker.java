package com.rabbit.think.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EvenChecker implements Runnable{

	private IntGenerator generator;
	private final int id;
	
	public EvenChecker(int id, IntGenerator ge) {
		this.id = id;
		this.generator = ge;
	}
	
	@Override
	public void run() {
		while(!generator.isCanceled()){
			int val = generator.next();
			generator.add1();
			System.out.println("Thread id: " + id + " int number : " + val);
			if(val % 2 != 0){
				System.out.println(val + "is not even!!!!!!!!!!!!!!");
				generator.cancel();
			}
		}
	}
	
	/**
	 * start thread
	 * @param generator
	 * @param count
	 */
	public static void start(IntGenerator generator, int count){
		System.out.println("Press Control-C to exit!");
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0; i<count; i++){
			exec.execute(new EvenChecker(i, generator));
		}
		exec.shutdown();
//		try {
//			exec.awaitTermination(2, TimeUnit.SECONDS);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void start(IntGenerator generator){
		start(generator, 10);
	}

}
