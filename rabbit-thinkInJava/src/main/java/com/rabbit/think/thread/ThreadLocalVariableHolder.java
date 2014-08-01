package com.rabbit.think.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder {
	
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
//		private Random random = new Random(47);
		protected synchronized Integer initialValue(){
//			return random.nextInt(10000);
			return 0;
		}
	};
	
	public ThreadLocalVariableHolder() {
	}
	
	public static void increment(){
		value.set(value.get() + 1);
	}
	
	public static int get(){
		return value.get();
	}
	
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i=0; i<5; i++){
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(2);
		exec.shutdown();
	}

}
