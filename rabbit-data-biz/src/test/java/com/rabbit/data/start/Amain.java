package com.rabbit.data.start;

public class Amain {
	
	public static int getM(AMI ami){
		ami.setM(4);
		return ami.getM(); 
	}
	
	public static void main(String[] args) {
		AMI ami = new AMImpl1();
		
		System.out.println(getM(ami));
	}

}
