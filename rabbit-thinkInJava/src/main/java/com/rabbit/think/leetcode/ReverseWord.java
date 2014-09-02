package com.rabbit.think.leetcode;
/**
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * 
 * @author rabbit
 * @date   Sep 2, 2014
 */
public class ReverseWord {

	public static void main(String[] args) {
		String s = "the sky is blue";
		String reverse = reverseWords(s);
		System.out.println(reverse);
	}
	
	public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
			return "";
		}
        StringBuffer sb = new StringBuffer();
        String[] ss = s.split(" ");
        for(int i=ss.length-1; i>=0; i--){
        	if(!ss[i].equals("")){
        		sb.append(ss[i]).append(" ");
        	}
        }
		return sb.toString().trim();
    }

}
