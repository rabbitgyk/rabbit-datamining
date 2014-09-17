package com.rabbit.think.leetcode;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * 题目给出两个字符串S和T，求S的所有子串中与T相同的有多少个。
 * A subsequence of a string is a new string which is formed from the original string 
 * by deleting some (can be none) of the characters without disturbing the relative 
 * positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" 
 * while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 * 
 * @author rabbit
 * @date   Sep 15, 2014
 */
public class DistinctSubsequences {
	
	public static void main(String[] args) {
		String S = "rababbit";
		String T = "rabbit";
		System.out.println(numDistinct(S, T));
	}
	
	public static int numDistinct(String S, String T) {
		if(S == null || T == null){
			return 0;
		}
		int[] cc = new int[T.length()];
		for(int i=S.length()-1; i>=0; i--){
			for(int j=0; j<T.length(); j++){
				if(S.charAt(i) == T.charAt(j)){
					if(j == T.length()-1)
						cc[j]++;
					else
						cc[j] = cc[j] + cc[j+1];
				}
			}
		}
		return cc[0];
    }
}
