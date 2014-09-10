package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct 
 * a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * 
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class WordBreak2 {
	
	public static void main(String[] args) {
		String s = "absd";
		Set<String> dict = new HashSet<String>();
		dict.add("ab");
		dict.add("s");
		dict.add("d");
		System.out.println(wordBreak(s, dict));
	}
	/**
	 * 使用了动态规划 dp
	 * @param s
	 * @param dict
	 * @return
	 */
	public static List<String> wordBreak(String s, Set<String> dict){
		if(s == null || dict == null)
			return null;
		
		int n = s.length();
		List<String> list = new ArrayList<String>();
		boolean[] dp = new boolean[n+1];
		dp[0] = true;
		for(int i=1; i<=n; i++){
			if(dp[i-1]){
				int index = i - 1;
				StringBuffer tmp = new StringBuffer();
				tmp.append(s.substring(0, index));
				for(int j=index; j<n; j++){
					String sub = s.substring(index, j+1);
					if(dict.contains(sub)){
						dp[j+1] = true;
					}
				}
			}
		}
		
		return dp[n];
	} 
	
}
