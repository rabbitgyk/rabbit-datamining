package com.rabbit.think.leetcode;

/**
 * Find the contiguous subarray within an array (containing at least one number) 
 * which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * 
 * @author rabbit
 * @date   Sep 27, 2014
 */
public class MaximumProductSubarray {
	
	//有可能成为最大乘积的是当前的数A[i],还有curMin * A[i] 和 curMax * A[i]
	public int maxProduct(int[] A) {
		int n = A.length;
        
        int max = A[0];
        int curMax = A[0];
        int curMin = A[0];
        for(int i=1; i<n; i++){
            int tmp = Math.min(A[i], Math.min(curMin * A[i], curMax * A[i]));
            curMax = Math.max(A[i], Math.max(curMin * A[i], curMax * A[i]));
            curMin = tmp;
            max = Math.max(max, curMax);
        }
        return max;
    }
}
