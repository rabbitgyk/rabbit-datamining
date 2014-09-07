package com.rabbit.think.leetcode;

/**
 * Given an array of integers, every element appears three times except for one. 
 * Find that single one.
 * Your algorithm should have a linear runtime complexity. 
 * Could you implement it without using extra memory?
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class SingleNumber2 {
	public static void main(String[] args) {
		int[] A = {2,2,4,2,3,5,4,5,4,5};
		System.out.println(singleNumber(A));
	}
	
	/**
	 * 所有数字的对应二进制位加起来，模上3的余数是单个数的对应二进制位数
	 * @param A
	 * @return
	 */
	public static int singleNumber(int[] A) {
        int[] count = new int[32];
        int result = 0;
		for(int i=0; i<32; i++){
			for(int j=0; j<A.length; j++){
				if(((A[j] >> i) & 1) == 1)
					count[i]++;
			}
			int tmp = (count[i] % 3) << i;
			result = result | tmp;
        }
		return result;
    }
}
