package com.rabbit.think.leetcode;

import java.util.HashMap;
/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.
 * get(key) - Get the value (will always be positive) of the key if the key 
 * exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. 
 * When the cache reached its capacity, it should invalidate the least recently 
 * used item before inserting a new item.
 * 每个节点都放在map中,key为节点的key，方便查找（还有一个原因，如果添加的节点key值重复的话，更新value不再添加）；
 * 所有节点形成一个双向链表，方便有顺序的插入删除；
 * @author rabbit
 * @date   Sep 5, 2014
 */
public class LRUCache {
	
	public static void main(String[] args) {
		LRUCache lc = new LRUCache(2);
		lc.set(2, 2);
		lc.set(3, 4);
		lc.set(4, 1);
		System.out.println(lc.get(3));
	}
	
	private HashMap<Integer, MEntry> map = null;
	private int capacity = 0;
	//两个辅助的头尾指针
	private MEntry head = null;
	private MEntry tail = null;

	public LRUCache(int capacity){
		map = new HashMap<Integer, MEntry>(capacity);
		head = new MEntry(-1, -1);
		tail = new MEntry(1, 1);
		head.next = tail;
		tail.pre = head;
		this.capacity = capacity;
	}
	
	public int get(int key){
		if(map.containsKey(key)){
			MEntry tEntry = map.get(key);
			tEntry.pre.next = tEntry.next;
			tEntry.next.pre = tEntry.pre;
			toHead(tEntry);
			return tEntry.value;
		}
		return -1;
	}
	/**
	 * 将节点t放在双向链表的头部
	 * @param t
	 */
	private void toHead(MEntry t){
		t.next = head.next;
		head.next.pre = t;
		t.pre = head;
		head.next = t;
	}
	
	public void set(int key, int value){
		if(map.containsKey(key)){
			MEntry m = map.get(key);
			m.value = value;
			m.pre.next = m.next;
			m.next.pre = m.pre;
			toHead(m);
			return;
		}
		if(map.size() >= capacity){
			MEntry p = removeTail();
			if(p != null){
				map.remove(p.key);
			}
		}
		MEntry mEntry = new MEntry(key, value);
		toHead(mEntry);
		map.put(key, mEntry);
	}
	/**
	 * 删除尾部节点
	 */
	private MEntry removeTail(){
		if(tail.pre != head){
			MEntry p = tail.pre;
			p.pre.next = tail;
			tail.pre = p.pre;
			return p;
		}
		return null;
	}
	
	class MEntry{
		int key;
		int value;
		MEntry pre;
		MEntry next;
		MEntry(int key, int value){
			this.key = key;
			this.value = value;
			this.pre = null;
			this.next = null;
		}
	}
}
