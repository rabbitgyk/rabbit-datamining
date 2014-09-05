package com.rabbit.think.leetcode;
/**
 * Given a linked list, return the node where the cycle begins. 
 * If there is no cycle, return null.
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * @author rabbit
 * @date   Sep 5, 2014
 */
public class LinkedListCycle2 {

	public static void main(String[] args) {
		
	}
	
	public ListNode detectCycle(ListNode head) {
		if(head == null)
			return null;
		boolean isCycle = false;
        ListNode p = head;
        ListNode q = head.next;
		while(q != null && q.next != null){
        	p = p.next;
        	q = q.next.next;
        	if(p == q){
        		isCycle = true;
        		break;
        	}
        }
		
		if(isCycle){
			p = head;
			q = q.next;
			while(true){
				if(p == q)
					return p;
				p = p.next;
				q = q.next;
			}
		}
		
		return null;
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
