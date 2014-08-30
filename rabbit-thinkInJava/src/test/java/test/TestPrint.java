package test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestPrint {
	int mun = 9;

	public static void main(String[] args) {
		System.out.printf("%15.2f\n", 3.12223);
		System.out.printf("%15.5f", 3.12223);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		ConcurrentHashMap<Integer, String> coMap = new ConcurrentHashMap<Integer, String>();

	}

}
