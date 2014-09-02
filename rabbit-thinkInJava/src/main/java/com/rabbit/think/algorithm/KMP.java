package com.rabbit.think.algorithm;
/**
 * 字符串的模式匹配(即从一个字符串中寻找另一个子串)算法KMP
 * @author rabbit
 * @date   Aug 30, 2014
 */
public class KMP {
	
	/**
	 * 模式匹配的简单算法
	 * @param str
	 * @param model
	 * @return 模式串在字符串str中的起始下标
	 */
	public static int simpleFind(char[] str, char[] model){
		int strLen = str.length;
		int modelLen = model.length;
		for(int i=0; i<strLen; i++){
			int tmpi = i;
			int tmpj = 0;
			while(tmpi < strLen && tmpj < modelLen && str[tmpi] == model[tmpj]){
				tmpi++;
				tmpj++;
			}
			if(tmpj == modelLen)
				return i;
		}
		return -1;
	}
	
	/**
	 * KMP算法实现
	 * @param str
	 * @param model
	 * @return 模式串在字符串str中的起始下标
	 */
	public static int realKMP(char[] str, char[] model){
		int[] next = next(model);
		int strLen = str.length;
		int modelLen = model.length;
		int i = 0,j = 0; //i控制str,j控制model;
		while(i < strLen){
			if(str[i] == model[j]){ //匹配就自动递增，往后匹配。
				if(j == modelLen - 1) //完全匹配了
					return (i - modelLen + 1);
				i++;
				j++;
			}else if(j == 0){
				i++;
			}else{
				j = next[j-1];
			}
		}
		return -1;
	}
	
	/**
	 * KMP中的核心算法，获得记录跳转状态的next数组
	 * @param model
	 * @return
	 */
	public static int[] next(char[] model){
		int len = model.length;
		int[] next = new int[len];
		next[0] = 0;
		for(int i=1; i<len; i++){
			int j = next[i-1];
			while(j > 0 && model[i] != model[j+1])
				j = next[j];
			if(model[i] == model[j+1])
				next[i] = j+1;
			else
				next[i] = 0;
		}
		return next;
	}
	
	public static void main(String[] args) {
		String a = "andfefdfiretiisfeweffsovkm";
		String model = "isfew";
		System.out.println(simpleFind(a.toCharArray(), model.toCharArray()));
		System.out.println("------------------------------------------------");
		System.out.println(realKMP(a.toCharArray(), model.toCharArray()));
	}

}
