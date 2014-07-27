package com.rabbit.data.start;

public class ExtendsBase {
	
	private int mo;
	
	public ExtendsBase(int mo){
		this.mo = mo;
	}
	
	public void output(){
		System.out.println("base mo: "+mo);
	}

	public int getMo() {
		return mo;
	}

	public void setMo(int mo) {
		this.mo = mo;
	}

}
