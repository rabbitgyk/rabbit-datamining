package com.rabbit.data.start;

public class BookTest {

	private int tr;
	
	public static void main(String[] args) {
		BookTest b = new BookTest(23);
		System.out.println(b.tr);
	}
	
	public BookTest(int te) {
		tr = te;
	}
}
