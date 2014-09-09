package test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestPrint {
	int mun = 9;

	public static void main(String[] args) {
//		System.out.printf("%15.2f\n", 3.12223);
//		System.out.printf("%15.5f", 3.12223);
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		ConcurrentHashMap<Integer, String> coMap = new ConcurrentHashMap<Integer, String>();
		map.put(1, "1ww");
		map.put(1, "2ww");
		map.put(null, "nullll");
		map.put(0, null);
		
		System.out.println("map get:"+(map.get(2)==null));
		
//		Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
//		numbers.put("one", 1);
//		numbers.put(null, 2);
//		numbers.put("three", 3);
//		numbers.put("three", 44); //如果key值相同，会冲掉旧值换新值
//
//		Integer n = numbers.get("three");
//		if (n != null) 
//			System.out.println("three = " + n);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		System.out.println(queue.isEmpty());
		queue.poll();
		System.out.println(queue.isEmpty());
	}
	
}
