package com.rabbit.think.leetcode;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m + n) 
 * to hold additional elements from B. The number of elements initialized 
 * in A and B are m and n respectively.
 * 
 * @author rabbit
 * @date   Sep 23, 2014
 */
public class MergeSortedArray {
	
	public void merge(int A[], int m, int B[], int n) {
        int ij = m + n -1;
        int i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0){
            if(A[i] >= B[j]){
                A[ij] = A[i];
                i--;
            }else{
                A[ij] = B[j];
                j--;
            }
            ij--;
        }
        while(j >= 0){
            A[ij] = B[j];
            j--;
            ij--;
        }
    }

}
