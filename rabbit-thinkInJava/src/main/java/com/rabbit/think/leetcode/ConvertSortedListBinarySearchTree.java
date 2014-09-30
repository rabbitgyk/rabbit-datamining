package com.rabbit.think.leetcode;

/**
 * Given a singly linked list where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 * 
 * @author rabbit
 * @date   Sep 30, 2014
 */
public class ConvertSortedListBinarySearchTree {

	public TreeNode sortedListToBST(ListNode head) {
		//零个元素
        if(head == null){
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = head.next;
        //一个元素
        if(p2 == null){
            return new TreeNode(head.val);
        }
        while(p2.next != null && p2.next.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        TreeNode tNode = new TreeNode(p1.next.val);
        ListNode head2 = p1.next.next;
        p1.next = null;
        tNode.left = sortedListToBST(head);
        tNode.right = sortedListToBST(head2);
        return tNode;
    }
	
	//Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; next = null; }
	}

	//Definition for binary tree
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
