package test;

import java.math.BigInteger;

public class TestPrint {
	int mun = 9;

	public static void main(String[] args) {
		BigInteger bi1 = new BigInteger("1");
		BigInteger bi2 = new BigInteger("2");
		BigInteger bi3 = new BigInteger("3");
		BigInteger sum = new BigInteger("0");
		sum = sum.add(bi1);
		sum = sum.add(bi2);
		sum = sum.add(bi3);
		System.out.println(sum.toString(2)); 
	} 
} 