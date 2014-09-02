package com.rabbit.think.thread.ProCon;

public class Waiter implements Runnable{
	private Restaurant restaurant;
	
	public Waiter(Restaurant res) {
		this.restaurant = res;
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null)
						wait(); // for the chef to produce a meal
				}
				System.out.println("waiter got " + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll(); // notify the chef ready for another
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("waiter thread interrupted!");
		}
	}

}
