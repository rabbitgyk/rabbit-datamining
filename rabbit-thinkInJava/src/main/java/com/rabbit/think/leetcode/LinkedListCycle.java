package com.rabbit.think.leetcode;
/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * @author rabbit
 * @date   Sep 5, 2014
 */
public class LinkedListCycle {

	public static void main(String[] args) {
		
	}
	
	public boolean hasCycle(ListNode head) {
		if(head == null)
			return false;
        ListNode p = head;
        ListNode q = head.next;
		while(q != null && q.next != null){
        	p = p.next;
        	q = q.next.next;
        	if(p == q)
        		return true;
        }
		return false;
    }
	
	static class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) {
	         val = x;
	         next = null;
	     }
	}
}
