package com.rabbit.think.leetcode;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents 
 * a point at coordinate (i, ai). n vertical lines are drawn such that 
 * the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, 
 * which together with x-axis forms a container, such that the container 
 * contains the most water.
 * Note: You may not slant the container.
 * 
 * @author rabbit
 * @date   Sep 17, 2014
 */
public class ContainerMostWater {

	public int maxArea(int[] height) {
        int max = 0;
		int head = 0;
        int tail = height.length-1;
        while(head < tail){
        	max = Math.max((tail - head) * Math.min(height[head], height[tail]), max);
        	if(height[head] < height[tail]){
        		head++;
        	}else{
        		tail--;
        	}
        }
        return max;
    }
	
}
