package com.rabbit.vip.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IPUtil {
	/**
	 * return 机器名
	 */
	public static String getHostName() {
		try {
			InetAddress ia = InetAddress.getByName("127.0.0.1");
			return ia.getHostName();
		} catch (UnknownHostException e) {
			return null;
		}
	}
	
	public static String getIpLocal() {
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = netInterfaces.nextElement();
				Enumeration<InetAddress> inetArresses = networkInterface
						.getInetAddresses();
				while (inetArresses.hasMoreElements()) {
					InetAddress inetAddress = inetArresses.nextElement();
					String ip = inetAddress.getHostAddress();
					byte[] addresses = inetAddress.getAddress();
					if (isIpV4(ip) && isPrivateIp(addresses)) {
						return ip;
					}
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return "";
	}

	private static boolean isPrivateIp(byte[] addressesByte) {
		int[] addresses = new int[4];
		for (int i = 0; i < 4; i++) {
			addresses[i] = getInt(addressesByte[i]);
		}
		if (isIpArressBetween(addresses, new int[] { 10, 0, 0, 0 }, new int[] {
				10, 255, 255, 255 })) {
			return true;
		}
		if (isIpArressBetween(addresses, new int[] { 172, 16, 0, 0 },
				new int[] { 172, 31, 255, 255 })) {
			return true;
		}
		if (isIpArressBetween(addresses, new int[] { 192, 168, 0, 0 },
				new int[] { 192, 168, 255, 255 })) {
			return true;
		}
		return false;
	}

	private static boolean isIpV4(String ip) {
		if (ip.matches("^(\\d)*.(\\d)*\\.(\\d)*\\.(\\d)*$")) {
			return true;
		}
		return false;
	}

	private static int compare(int[] ip1, int[] ip2) {
		for (int i = 0; i < ip1.length; i++) {
			if (ip1[i] == ip2[i]) {
				continue;
			} else if (ip1[i] > ip2[i]) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}

	private static boolean isIpArressBetween(int[] ipAddress, int[] ipBegin,
			int[] ipEnd) {
		if (compare(ipAddress, ipBegin) >= 0 && compare(ipAddress, ipEnd) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(getIpLocal());
	}

	private static int getInt(byte b) {
		int result = 255 & b;
		return result;
	}
}
