package com.rabbit.think.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *         1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 * 
 * @author rabbit
 * @date   Sep 15, 2014
 */
public class PopulateNextRight {
	
	public void connect(TreeLinkNode root) {
		if(root == null){
			return;
		}
		
		Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
		queue.offer(root);
		int count = 1;
		int pow = 1;
		TreeLinkNode p = null;
		while(!queue.isEmpty()){
			TreeLinkNode node = queue.poll();
			if(p != null){
				p.next = node;
				p = null;
			}
			if((count + 1) == Math.pow(2, pow)){
				node.next = null;
				pow++;
			}else{
				p = node;
			}
			if(node.left != null){
				queue.offer(node.left);
			}
			if(node.right != null){
				queue.offer(node.right);
			}
			count++;
		}
    }
	
	
	public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
}
