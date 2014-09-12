package com.rabbit.think.leetcode;

import java.util.regex.Pattern;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * 
 * @author rabbit
 * @date   Sep 12, 2014
 */
public class Palindrome {
	public static void main(String[] args) {
		String s = " sd, ;ds";
		System.out.println(isPalindrome(s));
//		System.out.println(isChar("a"));
	}
	public static boolean isPalindrome(String s) {
		if(s == null || s.length() == 0 || s.length() == 1){
	        return true;
	    }
	    StringBuffer sb = new StringBuffer();
		for(int i=0; i<s.length(); i++){
			String sub = s.substring(i, i+1);
			if(isChar(sub)){
				sb.append(sub);
			}
		}
		if(s == null || s.length() == 0 || s.length() == 1){
	        return true;
	    }
		String newString = sb.toString();
		int low = 0;
		int high = newString.length()-1;
		while(low < high){
			if(newString.substring(low, low+1).equalsIgnoreCase(newString.substring(high, high+1))){
				low++;
				high--;
			}else{
				break;
			}
		}
		if(high <= low)
			return true;
		return false;
	}
	
	private static boolean isChar(String c){
	    String model = "^[A-Za-z0-9]$";
	    return Pattern.matches(model, c);
	}
}
