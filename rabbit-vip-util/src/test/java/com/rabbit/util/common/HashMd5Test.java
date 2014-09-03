package com.rabbit.util.common;

import com.rabbit.vip.util.MD5Util;

public class HashMd5Test {
	
	public static void main(String[] args) {
		String str1 = "guoyankui";
		String str2 = "guoyankui1987";
		System.out.println(MD5Util.digest(str1));
		System.out.println(MD5Util.digest(str2));
	}

}
