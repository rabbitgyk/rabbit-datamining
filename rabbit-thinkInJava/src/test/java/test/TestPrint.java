package test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class TestPrint {
	int mun = 9;

	public static void main(String[] args) {
//		System.out.printf("%15.2f\n", 3.12223);
//		System.out.printf("%15.5f", 3.12223);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		ConcurrentHashMap<Integer, String> coMap = new ConcurrentHashMap<Integer, String>();
		
		map.put(1, "1ww");
		map.put(1, "2ww");
		map.put(null, "nullll");
		map.put(0, null);
		
		System.out.println("map get:"+map.get(1));
		
//		Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
//		numbers.put("one", 1);
//		numbers.put(null, 2);
//		numbers.put("three", 3);
//		numbers.put("three", 44); //如果key值相同，会冲掉旧值换新值
//
//		Integer n = numbers.get("three");
//		if (n != null) 
//			System.out.println("three = " + n);
	}
	
	int name() {
		return 2;
	}

}
