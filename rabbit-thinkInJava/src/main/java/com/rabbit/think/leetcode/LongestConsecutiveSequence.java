package com.rabbit.think.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 * 
 * @author rabbit
 * @date   Sep 10, 2014
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] num = {100,2,3,200,-1,0,1};
		System.out.println(longestConsecutive(num));
	}
	
	/**
	 * 解题思路：先将所有的数放进HashSet中，在逐个找连续序列，再找的过程中删除HashSet中被找过的元素，
	 * 可以基本保证O(n)
	 * 
	 * @param num
	 * @return
	 */
	public static int longestConsecutive(int[] num) {
        if(num == null || num.length == 0){
        	return 0;
        }
        
        Set<Integer> set = new HashSet<Integer>(num.length);
        for(int i=0; i<num.length; i++){
        	set.add(num[i]);
        }

        int maxLength = 1;
        for(int i=0; i<num.length; i++){
        	if(set.contains(num[i])){
        		int length = 1;
        		int next = num[i] - 1; //向前找
        		while(set.contains(next)){
        			length++;
        			set.remove(next);
        			next--;
        		}
        		next = num[i] + 1; //向后找
        		while(set.contains(next)){
        			length++;
        			set.remove(next);
        			next++;
        		}
        		if(length > maxLength){
        			maxLength = length;
        		}
        	}
        	set.remove(num[i]);
        }
        return maxLength;
    }
}
