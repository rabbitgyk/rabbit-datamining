package com.rabbit.think.algorithm;

public class SortHeap {
	
	public static void main(String[] args) {
		int[] numbers = {2,9,3,7,5,34,22,86};
		heapSort(numbers);
		for(int i=0; i<numbers.length; i++){
			System.out.print(numbers[i]+"  ");
		}
	}
	
	public static void heapSort(int[] numbers){
		int n = numbers.length;
		//建堆
		for(int i=(n-2)/2; i>=0; i--){
			adjustHeap(numbers, i, n-1);
		}
		//排序
		for(int i=n-1; i>=0; i--){
			int tmp = numbers[i];
			numbers[i] = numbers[0];
			numbers[0] = tmp;
			adjustHeap(numbers, 0, i-1);
		}
	}
	
	/**
	 * 调整一个元素的在堆中位置
	 * @param numbers 堆数组从0开始
	 * @param i 要调整的元素的位置
	 * @param last 堆的最后一个元素的位置
	 */
	private static void adjustHeap(int[] numbers, int i, int last){
		
		for(int j=2*i+1; j<=last; j=2*j+1){
			if(j < last && numbers[j] < numbers[j+1]){
				j++;
			}
			if(numbers[i] > numbers[j]){
				break;
			}
			//swap i j
			int target = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = target;
			i = j;
		}
	}
}
