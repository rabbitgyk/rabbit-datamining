package com.rabbit.think.leetcode;

/**
 * A linked list is given such that each node contains an additional random pointer 
 * which could point to any node in the list or null.
 * Return a deep copy of the list.
 * 
 * @author rabbit
 * @date   Sep 7, 2014
 */
public class CopyRandomList {
	
	public static void main(String[] args) {
		RandomListNode head = new RandomListNode(-1);
		head.next = null;
		head.random = null;
		RandomListNode cp = copyRandomList(head);
		System.out.println(cp.label);
		System.out.println(cp.next);
		System.out.println(cp.random);
		System.out.println(head.label);
		System.out.println(head.next);
		System.out.println(head.random);
	}
	
	/**
	 * 深度copy一条链，原链表不变
	 * 采用方法：
	 * 1.首先指向在原链表的每个节点后面，复制一个新的节点，原链表长度变为2倍
	 * 2.random 指针指向的是 原链表节点 random 指针指向的节点的后面的那个节点
	 * 3.将链表拆成两个 lists
	 * @param head
	 * @return
	 */
	public static RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
        	return null;
        
        //copy所有的节点并连接在原节点的后面
		RandomListNode p = head;
		while(p != null){
			RandomListNode tmp = new RandomListNode(p.label);
			tmp.random = p.random;
			tmp.next = p.next;
			p.next = tmp;
			p = p.next.next;
		}
		
		//random指针附上正确的值
		p = head;
		while(p != null){
			if(p.next.random != null){
				p.next.random = p.next.random.next;
			}
			p = p.next.next;
		}
		
		p = head;
		RandomListNode q = head.next.next;
		RandomListNode newHead = head.next;
		while(p != null && q != null){
			p.next.next = q.next;
			p.next = q;
			p = q;
			q = q.next.next;
		}
		if(q == null){
			p.next = null;
		}
		
		return newHead;
    }
	
	static class RandomListNode {
	    int label;
	    RandomListNode next, random;
	    RandomListNode(int x) { this.label = x; }
	}
}
