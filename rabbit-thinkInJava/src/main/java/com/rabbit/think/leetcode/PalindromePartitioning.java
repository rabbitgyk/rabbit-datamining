package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 *   
 * @author rabbit
 * @date   Sep 9, 2014
 */
public class PalindromePartitioning {
	
	public static void main(String[] args) {
		String s = "sdads";
		boolean[][] flags = partitionFlags(s);
		for(int i=0; i<flags.length; i++){
			for(int j=0; j<flags.length; j++){
				System.out.print(flags[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<List<String>>();
		if(s == null || s.length() == 0)
			return result;
		boolean[][] flags = partitionFlags(s);
		List<String> list = new ArrayList<String>();
		stringsDFS(s, 0, flags, list, result);
		return result;
    }
	
	/**
	 * 深度搜索s获取回文字符串数组
	 * @param s
	 * @param start
	 * @param flags
	 * @param list
	 * @param result
	 */
	private void stringsDFS(String s, int start, boolean[][] flags, List<String> list, List<List<String>> result){
		if(start == s.length()){
			result.add(list);
			return;
		}
		for(int i=start; i<s.length(); i++){
			if(flags[start][i]){
				List<String> tmp = new ArrayList<String>(list);
				tmp.add(s.substring(start, i+1));
				stringsDFS(s, i+1, flags, tmp, result);
			}
		}
	}
	
	/**
	 * 回文标志数组flags[i][j] == true,说明s[i...j]是回文串
	 * 动态规划生成回文标志数组
	 * @param s
	 * @return
	 */
	private static boolean[][] partitionFlags(String s){
		int n = s.length();
		boolean[][] flags = new boolean[n][n];
		for(int i=n-1; i>=0; i--){
			for(int j=i; j<n; j++){
				if(i == j){
					flags[i][j] = true;
				}else if(s.charAt(i) == s.charAt(j)){
					if((j-i) == 1 || flags[i+1][j-1])
						flags[i][j] = true;
				}
			}
		}
		return flags;
	}

}
