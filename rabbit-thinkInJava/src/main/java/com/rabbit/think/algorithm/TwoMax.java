package com.rabbit.think.algorithm;

public class TwoMax {

	public static void main(String[] args) {
		int[] data = {6,3,2,7,9,1,8};
		int init = Math.min(data[0], data[1]);
		int max = init;
		int max0 = init;
		for(int i=0; i<data.length; i++){
			if(data[i] > max){
				max0 = max;
				max = data[i];
			}else if(data[i] > max0 && data[i] < max){
				max0 = data[i];
			}
		}
		System.out.println("max:" + max + "  max0:" + max0);
	}
	
}
