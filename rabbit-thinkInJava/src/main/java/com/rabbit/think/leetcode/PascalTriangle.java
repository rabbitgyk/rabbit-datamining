package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * 
 * @author rabbit
 * @date   Sep 15, 2014
 */
public class PascalTriangle {

	public List<List<Integer>> generate(int numRows) {
		
		List<List<Integer>> triangle = new ArrayList<List<Integer>>(numRows);
		for(int i=0; i<numRows; i++){
			List<Integer> list = new ArrayList<Integer>(i+1);
			for(int j=0; j<i+1; j++){
				if(j == 0 || j == i){
					list.add(j, 1);
				}else{
					list.add(j, triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j));
				}
			}
			triangle.add(list);
		}
		
		
		return triangle;
    }

}
