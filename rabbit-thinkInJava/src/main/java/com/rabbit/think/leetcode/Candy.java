package com.rabbit.think.leetcode;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class Candy {

	public static void main(String[] args) {
		int[] ratings = {12,9,10,5,6};
		System.out.println(candy(ratings));
	}
	
	/**
	 * 将所有孩子遍历两遍，先从左到右，再从右到左
	 * @param ratings
	 * @return
	 */
	public static int candy(int[] ratings) {
		int n = ratings.length;
		int[] candys = new int[n];
		//每个孩子与其左侧的比较，满足条件
		candys[0] = 1;
		for(int i=1; i<n; i++){
			if(ratings[i] > ratings[i-1]){
				candys[i] = candys[i-1] + 1;
			}else{
				candys[i] = 1;
			}
		}
		//每个孩子与其右侧的比较，修正值,并计算最小总数
		int count = candys[n-1];
		for(int i=n-2; i>=0; i--){
			if(ratings[i] > ratings[i+1] && candys[i] <= candys[i+1]){
				candys[i] = candys[i+1] + 1;
			}
			count = count + candys[i];
		}
		
		return count;
    }
}
