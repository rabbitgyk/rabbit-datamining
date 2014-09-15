package com.rabbit.think.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * @author rabbit
 * @date   Sep 15, 2014
 */
public class PopulateNextRight2 {
	
	public static void main(String[] args) {
		TreeLinkNode s1 = new TreeLinkNode(3);
		TreeLinkNode s2 = s1;
		System.out.println(s1 == s2);
	}
	
	public void connect(TreeLinkNode root) {
		if(root == null){
			return;
		}
		
		Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
		queue.offer(root);
		TreeLinkNode head = root;
		TreeLinkNode cur = root;
		while(!queue.isEmpty()){
			TreeLinkNode node = queue.poll();
			if(node == head){
				cur.next = null;
				head = node.left;
			}else{
				cur.next = node;
			}
			if(head == null){
				head = (node.left != null) ? node.left : node.right;
			}
			cur = node;
			if(node.left != null){
				queue.offer(node.left);
			}
			if(node.right != null){
				queue.offer(node.right);
			}
		}
    }
	
	
	public static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
}
