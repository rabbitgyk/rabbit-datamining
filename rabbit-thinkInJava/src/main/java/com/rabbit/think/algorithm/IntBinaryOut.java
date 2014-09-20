package com.rabbit.think.algorithm;

public class IntBinaryOut {
	
	public static void main(String[] args) {
		System.out.println(getBinary(27, 2));
	}
	
	public static String getBinary(int number, int ary){
		StringBuilder sb = new StringBuilder();
		while(number != 0){
			sb.append(number % ary);
			number = number / ary;
		}
		//将sb逆序
		int len = sb.length() - 1;
		for(int i=0; i<=len/2; i++){
			char tmp = sb.charAt(i);
			sb.setCharAt(i, sb.charAt(len - i));
			sb.setCharAt(len - i, tmp);
		}
		return sb.toString();
	}
}
