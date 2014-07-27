package com.rabbit.data.start;

public class ExtendsModel extends ExtendsBase{

	private int mm;
	public ExtendsModel(int mo, int mm) {
		super(mo);
		this.mm = mm;
	}
	
	public void out(){
		System.out.println("mo,mm:"+getMo()+", "+mm);
	}

}
