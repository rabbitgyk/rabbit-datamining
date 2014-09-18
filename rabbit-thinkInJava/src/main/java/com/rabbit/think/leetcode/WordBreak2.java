package com.rabbit.think.leetcode;

import java.util.ArrayList;
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
	
	/**
	 * 使用了 dp + dfs
	 * @param s
	 * @param dict
	 * @return
	 */
	public static List<String> wordBreak(String s, Set<String> dict){
		ArrayList<String> result = new ArrayList<String>();
        if(s == null || dict.size() <= 0){
            return result;
        }
        int length = s.length();
        // seg(i, j) means substring t start from i and length is j can be
        // segmented into dictionary words
        boolean[][] seg = new boolean[length][length + 1];
        for(int len=1; len<=length; len++){
            for(int i=0; i<length-len+1; i++){
                String t = s.substring(i, i + len);
                if(dict.contains(t)){
                    seg[i][len] = true;
                    continue;
                }
                for(int k=1; k<len; k++){
                    if(seg[i][k] && seg[i + k][len - k]){
                        seg[i][len] = true;
                        break;
                    }
                }
            }
        }
        if(!seg[0][length]){
            return result;
        }

        int depth = 0;
        dfs(s, dict, result, seg, 0, length, depth, new StringBuffer());

        return result;
	}
	
	private static void dfs(String s, Set<String> dict, ArrayList<String> result, 
			boolean[][] seg, int start, int length, int depth, StringBuffer sb){
        if(depth == length){
            String t = sb.toString();
            result.add(t.substring(0, t.length() - 1));
            return;
        }

        for(int len=1; len<=length; len++){
            if(seg[start][len]){
                String t = s.substring(start, start + len);
                if(!dict.contains(t)){
                    continue;
                }
                int beforeAddLen = sb.length();
                sb.append(t).append(" ");
                dfs(s, dict, result, seg, start + len, length, start + len, sb);
                sb.delete(beforeAddLen, sb.length());
            }
        }
    }
	
}
