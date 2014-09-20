package com.rabbit.think.algorithm;

public class NNMatrix {

	public static void main(String[] args) {
		int n = 15;
		int[][] matrix = getNNMatrix(n);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				System.out.printf("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static int[][] getNNMatrix(int n){
		int[][] nn = new int[n][n];
		
		int i = 0;
		int j = 0;
		int a = 1;
		for(int k=0; k<(n+1)/2; k++){
			while(j < n-k){
				nn[i][j] = a;
				j++;
				a++;
			}
			j--;
			i++;
			while(i < n-k){
				nn[i][j] = a;
				i++;
				a++;
			}
			i--;
			j--;
			while(j > k-1){
				nn[i][j] = a;
				j--;
				a++;
			}
			j++;
			i--;
			while(i > k){
				nn[i][j] = a;
				i--;
				a++;
			}
			i++;
			j++;
		}
		return nn;
	}
}
