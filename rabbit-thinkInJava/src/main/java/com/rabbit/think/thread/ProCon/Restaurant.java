package com.rabbit.think.thread.ProCon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
	protected Meal meal;
	protected Waiter waiter;
	protected Chef chef;
	protected ExecutorService exec;
	
	public Restaurant(){
		waiter = new Waiter(this);
		chef = new Chef(this);
		exec = Executors.newCachedThreadPool();
		exec.execute(chef);
		exec.execute(waiter);
	}
	
	public static void main(String[] args) {
		new Restaurant();
	}
}
