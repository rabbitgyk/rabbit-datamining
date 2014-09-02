package com.rabbit.think.thread;


public class EvenGenerator extends IntGenerator{

	private int currentEvenValue = 0;
	@Override
	public synchronized int next() {
		++currentEvenValue;
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		++currentEvenValue;
		return currentEvenValue;
	}

	@Override
	public synchronized void add1() {
		++currentEvenValue;
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		++currentEvenValue;
	}
	
	public static void main(String[] args) {
		EvenChecker.start(new EvenGenerator());
	}
}
