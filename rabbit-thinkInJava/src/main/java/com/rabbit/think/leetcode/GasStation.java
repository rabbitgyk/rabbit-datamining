package com.rabbit.think.leetcode;

/**
 * There are N gas stations along a circular route, 
 * where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and 
 * it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, 
 * otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class GasStation {

	public static void main(String[] args) {
		int[] gas = {2,4,6,3,1};
		int[] cost = {2,1,5,3,3};
		System.out.println(canCompleteCircuit(gas, cost));
	}
	/**
	 * 如果能的话，两圈之内一定能找到合适的起点
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
		//求总油量和总需耗油量
		int gasSum = 0;
		int costSum = 0;
		for(int i=0; i<n; i++){
			gasSum = gasSum + gas[i];
			costSum = costSum + cost[i];
		}
		if(costSum > gasSum)
			return -1;
		
		int gasAmount = 0;
		int start = 0;
		for(int i=0; i<2*n; i++){
			int current = i % n;
			int next = (current + 1) % n;
			//预估进入下一站的
			gasAmount = gasAmount + gas[current] - cost[current];
			if(gasAmount >= 0){ //能跑到下一站
				if(next == start)
					return start;
			}else{
				gasAmount = 0;
				start = next;
			}
		}
		
		return -1;
    }
}
