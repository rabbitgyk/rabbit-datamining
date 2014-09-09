package com.rabbit.think.leetcode;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * @author rabbit
 * @date   Sep 9, 2014
 */
public class PalindromePartitioning2 {
	
	public static void main(String[] args) {
		String s = "adsdandsd";
		System.out.println(minCut(s));
	}
	
	/**
	 * 动态规划，状态数组minCut[i]表示从i开始到数组结尾的最小划分数
	 * @param s
	 * @return
	 */
	public static int minCut(String s) {
		int n = s.length();
		int[] minCut = new int[n + 1]; //从i开始到结尾的最小分割数
		boolean[][] flags = new boolean[n][n];
		for(int i=n-1; i>=0; i--){
			minCut[i] = n-i;
			for(int j=i; j<n; j++){
				if(s.charAt(i) == s.charAt(j)){
					if((j-i) < 2 || flags[i+1][j-1]){
						flags[i][j] = true;
						minCut[i] = Math.min(minCut[i], minCut[j+1] + 1);
					}
				}
			}
		}
		
		return minCut[0] - 1;
    }
	
}
