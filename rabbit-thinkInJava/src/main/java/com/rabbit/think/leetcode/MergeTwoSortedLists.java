package com.rabbit.think.leetcode;

/**
 * Merge two sorted linked lists and return it as a new list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 * 
 * @author rabbit
 * @date   Sep 27, 2014
 */
public class MergeTwoSortedLists {

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode head = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = head;
        while(p1 != null && p2 != null){
            if(p1.val <= p2.val){
                p3.next = p1;
                p1 = p1.next;
            }else{
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }
        if(p1 == null){
            p3.next = p2;
        }
        if(p2 == null){
            p3.next = p1;
        }
        return head.next;
    }


	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}

}
