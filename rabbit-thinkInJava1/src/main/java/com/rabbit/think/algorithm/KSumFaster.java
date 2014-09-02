package com.rabbit.think.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * K sum的求和问题一般是这样子描述的：
 * 给你一组N个数字(比如 int[] data), 然后给你一个常数(比如 int sum)，
 * 我们的goal是在这一组数里面找到K个数字，使得这K个数字的和等于sum。
 * @author rabbit
 * @date   Aug 30, 2014
 */
public class KSumFaster {
	
	private static int[] data = {3, 4, -5, 6, 8, 13, -4, -3, 9, 5, -6, 11, 7, 15, -7};
	
	public static void main(String[] args) {
		int k = 3;
		int sum = 0;
//		List<int[]> list = twoFind(0, sum);
		
		List<int[]> list = findAll(k, sum);
		for(int i=0; i<list.size(); i++){
			int[] arr = list.get(i);
			for(int j=0; j<arr.length; j++){
				System.out.print(arr[j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 找出两个数的和等于sum的数据对
	 * @param sum
	 * @return
	 */
	public static List<int[]> twoFind(int start, int sum){
		List<int[]> pairs = new ArrayList<int[]>();
		Arrays.sort(data);
		int head = start;
		int tail = data.length-1;
		while(head < tail){
			int twoSum = data[head] + data[tail];
			if(twoSum == sum){
				int[] pair = new int[2];
				pair[0] = data[head];
				pair[1] = data[tail];
				pairs.add(pair);
				head++;
				tail--;
			}else if(twoSum > sum){
				tail--;
			}else{
				head++;
			}
		}
		return pairs;
	}
	
	/**
	 * 找出k个数据的数据组合，使这k个数相加等于sum
	 * @param k
	 * @param sum
	 * @return
	 */
	public static List<int[]> findAll(int k, int sum){
		List<int[]> all = new ArrayList<int[]>();
		if(k == 2){
			all = twoFind(0, sum);
		}else if(k > 2){
			for(int i=0; i<data.length-k-1; i++){
				List<int[]> list = find(i, k, sum);
				all.addAll(list);
			}
		}else if(k < 2){
			System.out.println("k小于２没意义");
		}
		
		return all;
	}
	
	/**
	 * k sum的递归函数
	 * @param start
	 * @param k
	 * @param sum
	 * @return
	 */
	public static List<int[]> find(int start, int k, int sum){
		List<int[]> kpair = null;
		Arrays.sort(data);
		if(k > 2){
			kpair = find(start+1, k-1, sum-data[start]);
			for(int m=0; m<kpair.size(); m++){
				int[] p = kpair.get(m);
				int[] tem = new int[p.length+1];
				System.arraycopy(p, 0, tem, 0, p.length);
				tem[tem.length-1] = data[start];
				kpair.remove(m);
				kpair.add(m, tem);
			}
		}else if(k == 2){
			kpair = twoFind(start, sum);
		}
		return kpair;
	}

}


