package com.rabbit.think.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 求出小于等于N的素数。
 * @author rabbit
 * @date   Sep 22, 2014
 */
public class PrimeNumber {

	public static void main(String[] args) {
		List<Integer> ps = getPrimeN(11);
		for(int i=0; i<ps.size(); i++){
			System.out.print(ps.get(i)+" ");
		}
		System.out.println();
	}
	
	public static List<Integer> getPrimeN(int n){
		
		int[] an = new int[n + 1];
		//初始化为1
		for(int i=1; i<n+1; i++){
			an[i] = 1;
		}
		//标示一下素数和非素数an[i]=0,则i不是素数
		for(int i=2; i<n+1; i++){
			if(an[i] != 0){
				for(int j=i+i; j<n+1;){
					if(j % i == 0){
						an[j] = 0;
					}
					j = j + i;
				}
			}
		}
		//整合出所有的素数
		List<Integer> primes = new ArrayList<Integer>(); 
		for(int i=2; i<n+1; i++){
			if(an[i] != 0){
				primes.add(i);
			}
		}
		return primes;
	}
}
