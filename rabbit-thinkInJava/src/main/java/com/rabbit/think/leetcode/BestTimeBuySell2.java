package com.rabbit.think.leetcode;

/**
 * Say you have an array for which the ith element is the price of 
 * a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete 
 * as many transactions as you like (ie, buy one and sell one share 
 * of the stock multiple times). However, you may not engage in multiple 
 * transactions at the same time (ie, you must sell the stock before you buy again).
 * 
 * @author rabbit
 * @date   Sep 14, 2014
 */
public class BestTimeBuySell2 {

	public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for(int i=1; i<prices.length; i++){
        	if(prices[i] > prices[i-1]){
        		maxProfit = maxProfit + (prices[i] - prices[i-1]);
        	}
        }
        return maxProfit;
    }
}
