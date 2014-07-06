package com.rabbit.util.common;


import org.junit.Test;

public class StringUtilTest {

	@Test
	public void test() {
		System.out.println("hello junit");
	}
	
	@Test
	public void helloTest(){
		String s = "ni hao ma";
		StringUtil.hello(s);
	}
	

}
