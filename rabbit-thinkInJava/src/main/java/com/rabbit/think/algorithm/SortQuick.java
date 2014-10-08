package com.rabbit.think.algorithm;

public class SortQuick {
	
	public static void main(String[] args) {
		int[] numbers = {2,9,3,7,5,34,22,86};
		new SortQuick().quickSort(numbers, 0, numbers.length-1);
		for(int i=0; i<numbers.length; i++){
			System.out.print(numbers[i]+"  ");
		}
		
	}

	public void quickSort(int[] numbers, int low, int high){
		if(low >= high){
			return;
		}
		int mid = getPartition(numbers, low, high);
		quickSort(numbers, low, mid-1);
		quickSort(numbers, mid+1, high);
	}
	
	//将low的元素的终位置确定
	private int getPartition(int[] numbers, int low, int high){
		int midEle = numbers[low];
		while(low < high){
			while(low < high && numbers[high] >= midEle){
				high--;
			}
			numbers[low] = numbers[high];
			while(low < high && numbers[low] <= midEle){
				low++;
			}
			numbers[high] = numbers[low];
		}
		numbers[low] = midEle;
		return low;
	}
	
}
