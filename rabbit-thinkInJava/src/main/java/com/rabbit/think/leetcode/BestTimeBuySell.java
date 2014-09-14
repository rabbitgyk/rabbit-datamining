package com.rabbit.think.leetcode;

/**
 * Say you have an array for which the ith element is the price of 
 * a given stock on day i.
 * If you were only permitted to complete at most one transaction 
 * (ie, buy one and sell one share of the stock), design an algorithm 
 * to find the maximum profit.
 * 
 * @author rabbit
 * @date   Sep 14, 2014
 */
public class BestTimeBuySell {
	
	public int maxProfit(int[] prices) {
		if(prices.length < 2){
			return 0;
		}
        int maxDiff = 0;
        int minValue = prices[0];
        for(int i=1; i<prices.length; i++){
        	int tmp = prices[i] - minValue;
        	if(tmp > maxDiff){
        		maxDiff = tmp;
        	}
        	if(prices[i] < minValue){
        		minValue = prices[i];
        	}
        }
        return maxDiff;
    }

}
