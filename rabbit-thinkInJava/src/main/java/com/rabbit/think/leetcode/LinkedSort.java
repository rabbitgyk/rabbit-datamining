package com.rabbit.think.leetcode;

import java.util.Random;
/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * @author rabbit
 * @date   Sep 4, 2014
 */
public class LinkedSort {

	public static void main(String[] args) {
//		ListNode head1 = new ListNode(0);
//		ListNode head2 = new ListNode(1);
//		ListNode p = head1;
//		for(int i=0; i<5; i++){
//			p.next = new ListNode(2*i+2);
//			p = p.next;
//		}
//		p = head2;
//		for(int i=1; i<5; i++){
//			p.next = new ListNode(2*i+1);
//			p = p.next;
//		}
//		printLink(head1);
//		printLink(head2);
//		ListNode head = merge(head1, head2);
//		printLink(head);
//		System.out.println(getMid(head).val);
		ListNode head = new ListNode(1);
		ListNode p = head;
		Random random = new Random(1);
		for(int i=0; i<10; i++){
			p.next = new ListNode(random.nextInt(20));
			p = p.next;
		}
		printLink(head);
		printLink(sortList(head));
	}
	
	/**
	 * 归并排序
	 * @param head
	 * @return
	 */
	public static ListNode sortList(ListNode head) {
		if(head == null)
			return null;
		if(head.next == null){
			return head;
		}
        ListNode mid = getMid(head);
		ListNode head1 = head;
		ListNode head2 = mid.next;
		mid.next = null;
		
		return merge(sortList(head1), sortList(head2));
    }
	/**
	 * 合并两个有序的链表
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static ListNode merge(ListNode head1, ListNode head2){
		if(head1 == null){
			return head2;
		}
		if(head2 == null){
			return head1;
		}
		
		ListNode a = head1;
		ListNode b = head2;
		ListNode head = new ListNode(-1);
		ListNode p = head;
		while(a != null && b != null){
			if(a.val <= b.val){
				p.next = a;
				a = a.next;
			}else{
				p.next = b;
				b = b.next;
			}
			p = p.next;
		}
		if(a == null){
			p.next = b;
		}
		if(b == null){
			p.next = a;
		}	
		head = head.next;
		return head;
	}
	/**
	 * 获得链表的中间节点
	 * @param head
	 * @return
	 */
	public static ListNode getMid(ListNode head){
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
