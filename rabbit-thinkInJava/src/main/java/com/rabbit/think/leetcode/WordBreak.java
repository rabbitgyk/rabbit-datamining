package com.rabbit.think.leetcode;

import java.util.HashSet;
import java.util.Set;
/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a 
 * space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class WordBreak {
	
	public static void main(String[] args) {
		String s = "absd";
		Set<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("ab");
		dict.add("s");
		dict.add("bsd");
		dict.add("d");
		System.out.println(wordBreak_DP(s, dict));
	}
	/**
	 * 非dp的解法
	 * @param s
	 * @param dict
	 * @return
	 */
	public static boolean wordBreak(String s, Set<String> dict) {
		if(dict.contains(s))
			return true;
	    int n = s.length();

	    // search from back to stop early if no tailing word is in the dict.
	    // without this part will get TLE in "aaaaaa.......aab" case.
	    boolean earlyStop = true;
	    for(int i=n-1; i>0; i--){
	    	if(dict.contains(s.substring(i))){
	            earlyStop = false;
	            break;
	        }
	    }
	    if(earlyStop)
	    	return false;

	    // 搜索串的前缀，从长到短能节省时间（长串存在dict中的可能性小于短串）
	    for (int i=n-1; i>0; i--) {
	        String s1 = s.substring(0, i);
	        if (dict.contains(s1)) {
	            String s2 = s.substring(i);
	            if (wordBreak(s2, dict))
	                return true;
	        }
	    }
	    return false;
    }
	/**
	 * 使用了动态规划 dp
	 * @param s
	 * @param dict
	 * @return
	 */
	public static boolean wordBreak_DP(String s, Set<String> dict){
		if(s == null || dict == null)
			return false;
		
		int n = s.length();
		boolean[] dp = new boolean[n+1];
		dp[0] = true;
		for(int i=1; i<=n; i++){
			if(dp[i-1]){
				int index = i - 1;
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
