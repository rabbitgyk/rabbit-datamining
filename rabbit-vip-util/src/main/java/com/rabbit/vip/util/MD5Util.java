package com.rabbit.vip.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	/**
	 * 加密类型
	 * @author tf
	 *
	 */
	public static enum DigestType {
		MD5, SHA1;
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

	private static byte[] computeLargeByte(byte[] bytes) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		md5.reset();
		md5.update(ByteBuffer.wrap(bytes));
		return md5.digest();
	}

	public static final String digest(byte[] bytes) {
		byte[] md = computeLargeByte(bytes);
		return StringUtil.bytesToHex(md);

	}

	private static final byte[] digestSHA1(String msg) {
		MessageDigest sha1;
		try {
			sha1 = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 not supported", e);
		}
		sha1.reset();
		sha1.update(ByteBuffer.wrap(msg.getBytes()));
		return sha1.digest();
	}

	public static void main(String[] args) {
		System.out.println(digest("nx123NX#",DigestType.SHA1));
	}

	public static final String digest(String message, DigestType type) {
		byte[] md = null;
		if (type.equals(DigestType.MD5)) {
			md = computeString(message);
		}else if(type.equals(DigestType.SHA1)){
			md = digestSHA1(message);
		}
		return StringUtil.bytesToHex(md);
	}

	public static final String digest(String message) {
		byte[] md = computeString(message);
		return StringUtil.bytesToHex(md);
	}

	public static BigInteger digestNum(String message) {
		byte[] bs = computeString(message);
		byte[] bs2 = new byte[8];
		for (int i = 0; i < bs2.length; i++)
			bs2[i] = bs[i];
		return new BigInteger(1, bs2);
	}
}
