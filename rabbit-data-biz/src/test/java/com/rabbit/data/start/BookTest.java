package com.rabbit.data.start;

public class BookTest {
	
	public static void main(String[] args) {
		ExtendsModel em = new ExtendsModel(2, 3);
		em.out();
		
		if(em instanceof ExtendsBase){
			System.out.println("em is ExtendsBase");
		}
		if(em instanceof ExtendsModel){
			System.out.println("em is ExtendsModel");
		}
	}
}
