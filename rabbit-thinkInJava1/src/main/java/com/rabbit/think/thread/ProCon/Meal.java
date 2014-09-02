package com.rabbit.think.thread.ProCon;

/**
 * 食物
 * @author rabbit
 * @date   Aug 3, 2014
 */

public class Meal {
	private final int orderNum;
	
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	
	public String toString(){
		return "Meal: " + orderNum;
	}
}
