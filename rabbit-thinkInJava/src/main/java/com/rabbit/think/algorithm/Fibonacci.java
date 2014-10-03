package com.rabbit.think.algorithm;

public class Fibonacci {
	
	public static void main(String[] args) {
		System.out.println(nthItem(10));
		System.out.println(nthItem1(10));
	}
	
	//递归的解法
	public static long nthItem(int n){
		if(n <= 0){
			return 0;
		}
		if(n == 1){
			return 1;
		}
		return nthItem(n-1) + nthItem(n-2);
	}
	
	//非递归的迭代
	public static long nthItem1(int n){
		if(n <= 0){
			return 0;
		}
		if(n == 1){
			return 1;
		}
		// if n > 2
		long fibo1 = 0;
		long fibo2 = 1;
		long fibo = 0;
		for(int i=1; i < n; i++){
			fibo = fibo1 + fibo2;
			fibo1 = fibo2;
			fibo2 = fibo;
		}
		return fibo;
	}
	
}
