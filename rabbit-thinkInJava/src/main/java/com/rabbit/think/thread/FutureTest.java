package com.rabbit.think.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	
	List<Future<String>> list = new ArrayList<Future<String>>();
	
	public static void main(String[] args) throws Exception{
		FutureTest ft = new FutureTest();
		ft.multiCompute();
		List<Future<String>> list = ft.list;
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).get());
		}
	}
	
	public void multiCompute(){
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for(int i=0; i<5; i++){
			Future<String> fu = pool.submit(new CallableThread(i));
			list.add(fu);
		}
		pool.shutdown();
		System.out.println("task is ok");
	}
	
	
	class CallableThread implements Callable<String> {
		int id = 0;
		int count = 0;
		
		public CallableThread(int id) {
			this.id = id;
		}
		@Override
		public String call() throws Exception {
			for(int i=0; i<100; i++){
				count = count + i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
			return "Thread id : " + id +"      result : "+ count;
		}
	}

}
