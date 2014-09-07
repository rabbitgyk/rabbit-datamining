package com.rabbit.think.leetcode;

/**
 * Given an array of integers, every element appears twice except for one. 
 * Find that single one.
 * Your algorithm should have a linear runtime complexity. 
 * Could you implement it without using extra memory?
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class SingleNumber {
	
	public static void main(String[] args) {
		int[] A = {2,2,3,4,5,4,5};
		System.out.println(singleNumber(A));
	}
	
	/**
	 * 两个相同的数异或为0,任何数与0异或还是本身
	 * @param A
	 * @return
	 */
	public static int singleNumber(int[] A) {
		for(int i=1; i<A.length; i++){
			A[0] = A[0] ^ A[i];
		}
		return A[0];
    }
}
