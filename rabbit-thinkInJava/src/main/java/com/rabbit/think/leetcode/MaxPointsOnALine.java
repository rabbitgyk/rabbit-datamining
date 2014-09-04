package com.rabbit.think.leetcode;

import java.util.HashMap;

/**
 * Given n points on a 2D plane, find the maximum number of points 
 * that lie on the same straight line.
 * 
 * @author rabbit
 * @date   Sep 4, 2014
 */
public class MaxPointsOnALine {
	
	public static void main(String[] args) {
		Point p0 = new Point(2, 3);
		Point p1 = new Point(3, 3);
		Point p2 = new Point(-5, 3);
		Point p3 = new Point(5, 3);
//		Point p4 = new Point(-5, 1);
		Point[] points = {p0, p1, p2, p3};
		System.out.println(maxPoints(points));
	}
	
	/**
	 * 暴力的一个个点算斜率
	 * @param points
	 * @return
	 */
	public static int maxPoints(Point[] points){
		int pointsNum = points.length;
		if(pointsNum <= 2){
			return pointsNum;
		}
		int maxNum = 0;
		HashMap<Double, Integer> kNum = new HashMap<Double, Integer>();
		for(int i=0; i<pointsNum; i++){
			Point p1 = points[i];
			kNum.clear();
			int sameNum = 0;
			for(int j=i+1; j<pointsNum; j++){
				Point p2 = points[j];
				double k = 0;
				if(p2.y == p1.y && p2.x == p1.x){
					sameNum++;
					continue;
				}else if(p2.x == p1.x){
					k = Double.POSITIVE_INFINITY;
				}else{
					k = (double)(p2.y - p1.y) / (p2.x - p1.x);
					if(k == -0.0)
						k = 0.0;
				}
				if(!kNum.containsKey(k)){
					kNum.put(k, 2);
				}else{
					kNum.put(k, kNum.get(k) + 1);
				}
			}
			int pmax = 1;
			for(double k : kNum.keySet()){
				int value = kNum.get(k);
				if(value > pmax){
					pmax = value;
				}
			}
			pmax = pmax + sameNum;
			if(pmax > maxNum){
				maxNum = pmax;
			}
		}
		
		return maxNum;
	}
	
	
	/**
	 * 点的定义
	 * @author rabbit
	 * @date   Sep 4, 2014
	 */
	static class Point {
		int x;
		int y;
		Point(){
			x = 0;
			y = 0;
		}
		Point(int a, int b){
			x = a;
			y = b;
		}
	}

}
