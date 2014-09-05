package com.rabbit.think.leetcode;

public class ReorderList {
	
	public static void main(String[] args) {
		ListNode head1 = new ListNode(0);
		ListNode p = head1;
		for(int i=0; i<11; i++){
			p.next = new ListNode(2*i+2);
			p = p.next;
		}
		printLink(head1);
		reorderList(head1);
		printLink(head1);
	}

	public static void reorderList(ListNode head) {
        if(head == null || head.next == null)
        	return;
        
        ListNode mid = getMid(head);
        ListNode lastHalf = mid.next;
        mid.next = null;
        lastHalf = reverseList(lastHalf);
        printLink(head);
        printLink(lastHalf);
        ListNode p = head;
        while(lastHalf != null){
        	ListNode q = lastHalf;
        	lastHalf = lastHalf.next;
        	q.next = p.next;
        	p.next = q;
        	p = p.next.next;
        }
    }
	
	/**
	 * 获得链表的中间节点
	 * @param head
	 * @return
	 */
	private static ListNode getMid(ListNode head){
		if(head == null)
			return null;
		if(head.next == null)
			return head;
		ListNode slow = head;
		ListNode fast = head.next;
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	/**
	 * 单链表就地翻转逆序
	 * @param head
	 * @return
	 */
	private static ListNode reverseList(ListNode head){
		ListNode h = new ListNode(-1);
		h.next = head;
		head = h;
		
		ListNode p = head.next;
		if(p == null)
			return null;
		while(p.next != null){
			ListNode q = p.next;
			p.next = q.next;
			q.next = head.next;
			head.next = q;
		}
		return head.next;
	}
	/**
	 * 打印链表的值
	 * @param head
	 */
	public static void printLink(ListNode head){
		ListNode p = head;
		while(p != null){
			System.out.print(" "+p.val);
			p = p.next;
		}
		System.out.println();
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
