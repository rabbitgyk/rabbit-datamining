package com.rabbit.think.leetcode;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note:
 * You may not engage in multiple transactions at the same time 
 * (ie, you must sell the stock before you buy again).
 * 
 * @author rabbit
 * @date   Sep 14, 2014
 */
public class BestTimeBuySell3 {

	public int maxProfit(int[] prices) {
		if(prices.length == 0)
            return 0;
		
		//从前到后，index i 之前的最大收益
		int[] maxp1 = new int[prices.length];
		maxp1[0] = 0;
		int minPrice = prices[0];
		for(int i=1; i<prices.length; i++){
			if(minPrice > prices[i]){
				maxp1[i] = maxp1[i-1];
				minPrice = prices[i];
			}else{
				maxp1[i] = Math.max(maxp1[i-1], (prices[i] - minPrice));
			}
		}
		//从后向前，index i 之后的最大收益
		int[] maxp2 = new int[prices.length];
		maxp2[prices.length-1] = 0;
		int maxPrice = prices[prices.length-1];
		for(int j=prices.length-2; j>=0; j--){
			if(maxPrice < prices[j+1]){
				maxp2[j] = maxp2[j+1];
				maxPrice = prices[j+1];
			}else{
				maxp2[j] = Math.max(maxp2[j+1], (maxPrice - prices[j+1]));
			}
		}
		
		int max = 0;
		for(int i=0; i<prices.length; i++){
			if(max < (maxp1[i] + maxp2[i])){
				max = maxp1[i] + maxp2[i];
			}
		}
		
		return max;
    }
}
