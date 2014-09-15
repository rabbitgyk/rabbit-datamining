package com.rabbit.think.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 * 
 * @author rabbit
 * @date   Sep 15, 2014
 */
public class PascalTriangle2 {
	
	public static void main(String[] args) {
		System.out.println(getRow(0));
//		Queue<Integer> list = new LinkedList<Integer>();
////		list.add(3);
////		list.add(5);
////		list.add(4);
//		System.out.println(list);
//		System.out.println(list.poll());
	}
	
	@SuppressWarnings("unchecked")
	public static List<Integer> getRow(int rowIndex) {
        Queue<Integer> row = new LinkedList<Integer>();
        int cur = 0;
        for(int i=0; i<rowIndex+1; i++){
        	for(int j=0; j<i; j++){
        		if(j == 0){
        			cur = row.poll();
        			row.offer(1);
				}else{
					int peek = row.peek();
					row.offer(peek + cur);
					cur = row.poll();
				}
        	}
        	row.offer(1);
        }
        return (List<Integer>) row;
    }
	
}
