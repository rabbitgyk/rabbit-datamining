package com.rabbit.think.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (start and end), and a dictionary, find the length of 
 * shortest transformation sequence from start to end, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * For example,
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * @author rabbit
 * @date   Sep 19, 2014
 */
public class WordLadder {
	
	public static void main(String[] args) {
		String start = "hit";
		String end = "cog";
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("log");
		System.out.println(ladderLength(start, end, dict));
	}
	
	public static int ladderLength(String start, String end, Set<String> dict) {
		if(start == null || end == null 
				|| start.equals(end) || start.length() != end.length())
			return 0;
		if(oneDiff(start, end))
			return 2;
		
        String p = start;
      //记录访问过的单词和所处的层次数
        Map<String, Integer> visited = new HashMap<String, Integer>(); 
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        visited.put(start, 1);
        while(!queue.isEmpty()){
        	p = queue.poll();
        	int cur = visited.get(p);
        	//通过替换字母来找一个字母不同的单词
        	for(int i=0; i<p.length(); i++){
        		for(char j='a'; j<='z'; j++){
        			if(p.charAt(i) == j){
        				continue;
        			}
        			StringBuilder sb = new StringBuilder(p);
        			sb.setCharAt(i, j);
        			String tmp = sb.toString();
        			if(!visited.containsKey(tmp) && dict.contains(tmp)){
        				queue.offer(tmp);
        				visited.put(tmp, cur + 1);
        			}
        			if(tmp.equals(end)){
        	        	return cur+1;
        	        }
        		}
        	}
        }
        return 0;
    }
	
    /**
     * a, b两个单词只有一个字母不同
     * @param a
     * @param b
     * @return
     */
    private static boolean oneDiff(String a, String b){
    	int count = 0;
    	for(int i=0; i<a.length(); i++){
    		if(a.charAt(i) != b.charAt(i)){
    			count++;
    		}
    		if(count > 1){
    			return false;
    		}
    	}
    	if(count < 1){
    		return false;
    	}
    	return true;
    }

}
