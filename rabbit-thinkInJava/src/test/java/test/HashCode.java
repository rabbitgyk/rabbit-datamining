package test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCode {
	public static void main(String[] args) {
		String str1 = "guoyankui";
		int str1Hash = str1.hashCode();
		int str1Hash32 = sun.misc.Hashing.murmur3_32(str1.toCharArray());
		System.out.println("str1Hash:"+str1Hash);
		System.out.println("str1Hash32:"+str1Hash32);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(computeString(str1));
	}
	
	private static byte[] computeString(String k) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		try {
			md5.update(k.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5.digest();
	}
}
