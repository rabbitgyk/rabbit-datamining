package com.rabbit.think.algorithm;

/**
 * 在一个整数数组中，找出最长的连续数字数（顺序或逆序）
 * 
 * @author rabbit
 * @date   Sep 11, 2014
 */
public class LongestContinueNums {

	public static void main(String[] args) {
		int[] num = {-1,0,1};
		System.out.println(longestConsecutive(num));
	}
	public static int longestConsecutive(int[] num) {
        if(num == null || num.length == 0){
        	return 0;
        }
		int maxLen = 1;
		int curLen = 1;
		int order = 0; //1表示升序，-1表示降序
		for(int i=1; i<num.length; i++){
			if(num[i] == (num[i-1] + 1)){
				if(order == 1){
					curLen++;
				}else{
					if(curLen > maxLen){
						maxLen = curLen;
					}
					curLen = 2;
				}
				order = 1;
			}else if(num[i] == (num[i-1] - 1)){
				if(order == -1){
					curLen++;
				}else{
					if(curLen > maxLen){
						maxLen = curLen;
					}
					curLen = 2;
				}
				order = -1;
			}else{
				if(curLen > maxLen){
					maxLen = curLen;
				}
				curLen = 1;
			}
		}
		if(curLen > maxLen){
			maxLen = curLen;
		}
		return maxLen;
    }
}
