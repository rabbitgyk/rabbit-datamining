package com.rabbit.think.leetcode;

/**
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * For example:
 * Given the below binary tree,
 *        1
 *       / \
 *      2   3
 * Return 6.
 * 有路径的两点之间的所有节点求和，最大值
 * 
 * @author rabbit
 * @date   Sep 14, 2014
 */
public class SumBTreeMaxPath {
	
	int maxValue = Integer.MIN_VALUE;
	
	public int maxPathSum(TreeNode root) {
		if(root == null){
			return 0;
		}
		maxPath(root);
		return maxValue;
    }
	
	private int maxPath(TreeNode node){
		if(node == null){
			return 0;
		}
		int value = node.val; //返回单向的最大值
		int left = maxPath(node.left);
		int right = maxPath(node.right);
		if(left > right){
			value = (node.val > (node.val + left)) ? node.val : node.val + left;
		}else{
			value = (node.val > (node.val + right)) ? node.val : node.val + right;
		}
		//更新最大值
		int tmp = (value > node.val + left + right) ? value : node.val + left + right;
		if(maxValue < tmp){
			maxValue = tmp;
		}
		return value;
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
