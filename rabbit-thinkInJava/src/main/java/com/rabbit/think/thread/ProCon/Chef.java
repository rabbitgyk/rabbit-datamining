package com.rabbit.think.thread.ProCon;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable{
	private Restaurant restaurant;
	private int count = 0;
	
	public Chef(Restaurant res) {
		this.restaurant = res;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null)
						wait(); // wait for the meal to be taken
				}
				if (++count == 10) { // 餐厅的食物卖完了，关门
					System.out.println("Out of food, closing!");
					restaurant.exec.shutdownNow();
				}
				System.out.println("order up!");
				synchronized (restaurant.waiter) {
					restaurant.meal = new Meal(count);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("chef thread interrupter!!");
		}
	}

}
