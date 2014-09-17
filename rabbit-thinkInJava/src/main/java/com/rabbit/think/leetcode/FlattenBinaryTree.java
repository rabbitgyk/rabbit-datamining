package com.rabbit.think.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * 
 * @author rabbit
 * @date   Sep 17, 2014
 */
public class FlattenBinaryTree {

	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	public void flatten(TreeNode root) {
        if(root == null){
        	return;
        }
        traversal(root);
        TreeNode front = queue.poll();
        front.left = null;
        while(!queue.isEmpty()){
        	TreeNode p = queue.poll();
        	p.left = null;
        	front.right = p;
        	front = p;
        }
    }
	/**
	 * 树的先序遍历
	 * @param node
	 */
	private void traversal(TreeNode node){
		queue.offer(node);
		if(node.left != null){
			traversal(node.left);
		}
		if(node.right != null){
			traversal(node.right);
		}
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
