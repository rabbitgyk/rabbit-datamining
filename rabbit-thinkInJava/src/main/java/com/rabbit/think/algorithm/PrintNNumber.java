package com.rabbit.think.algorithm;

/**
 * 输出从1到最大的n位数之间的所有整数。
 * 
 * @author rabbit
 * @date   Oct 5, 2014
 */
public class PrintNNumber {
	
	public static void main(String[] args) {
		new PrintNNumber().maxNNumber(1);
	}

	public void maxNNumber(int n){
		char[] number = new char[n];
		//初始化
		for(int i=0; i<n; i++){
			number[i] = '0';
		}
		while(!increment(number)){
			System.out.println(number);
		}
	}
	
	 //number + 1
	 private boolean increment(char[] number){
		 int n = number.length;
		 boolean overflow = false;
		 int take = 0;
		 for(int i=n-1; i>=0; i--){
			 int inum = number[i] - '0' + take;
			 if(i == n-1){
				 inum++;
			 }
			 if(inum >=10){
				 if(i == 0){
					 overflow = true;
				 }else{
					 inum = inum - 10;
					 take = 1;
					 number[i] = (char) ('0' + inum);
				 }
			 }else{
				 number[i] = (char) ('0' + inum);
				 break;
			 }
		 }
		 return overflow;
	 }
}
