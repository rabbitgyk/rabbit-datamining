package com.rabbit.think.leetcode;

import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, 
 * where n is the total number of rows in the triangle.
 * 
 * @author rabbit
 * @date   Sep 15, 2014
 */
public class Triangle {

	public int minimumTotal(List<List<Integer>> triangle) {
		
		if(triangle == null || triangle.size() == 0){
            return 0;
        }
        int rows = triangle.size();
        if(rows == 1){
            return triangle.get(0).get(0);
        }
        
        List<Integer> last = triangle.get(rows-1);
        int[] minPath = new int[last.size()];
        for(int i=0; i<last.size(); i++){
        	minPath[i] = last.get(i);
        }
        for(int i=rows-2; i>=0; i--){
        	List<Integer> cur = triangle.get(i);
        	for(int j=0; j<cur.size(); j++){
        		minPath[j] = cur.get(j) + Math.min(minPath[j], minPath[j+1]);
        	}
        }
		
		return minPath[0];
	}
}
