package com.rabbit.think.leetcode;

import java.util.Random;
/**
 * Sort a linked list using insertion sort.
 * @author rabbit
 * @date   Sep 5, 2014
 */
public class LinkedInsertSort {
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode p = head;
		Random random = new Random(1);
		for(int i=0; i<10; i++){
			p.next = new ListNode(random.nextInt(20));
			p = p.next;
		}
		printLink(head);
		printLink(insertionSortList(head));
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
	
	public static ListNode insertionSortList(ListNode head){
		if(head == null || head.next == null)
			return head;
		//head 前加一个辅助节点
		ListNode ll = new ListNode(-1);
		ll.next = head;
		head = ll;
		ListNode node = head.next.next;
		head.next.next = null;
		while(node != null){
			ListNode a = head;
			while(a != null){
				if(a.next == null){
					ListNode tmp = node;
					node = node.next;
					tmp.next = null;
					a.next = tmp;
					break;
				}else if(a.next.val <= node.val){
					a = a.next;
				}else{
					ListNode tmp = node;
					node = node.next;
					tmp.next = a.next;
					a.next = tmp;
					break;
				}
			}
		}
		return head.next;
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
