package com.rabbit.think.leetcode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path 
 * from the root node down to the nearest leaf node.
 * 
 * @author rabbit
 * @date   Sep 17, 2014
 */
public class MiniDepthBinaryTree {

	int min = Integer.MAX_VALUE;
	int cur = 0;
	
	public int minDepth(TreeNode root) {
        if(root == null){
        	return 0;
        }
        depthHelper(root);
		return min;
    }
	
	private void depthHelper(TreeNode root){
		cur++;
        if(root.left == null && root.right == null){
        	if(cur < min){
        		min = cur;
        	}
        }
        if(root.left != null){
        	depthHelper(root.left);
        }
        if(root.right != null){
        	depthHelper(root.right);
        }
        cur--;
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
