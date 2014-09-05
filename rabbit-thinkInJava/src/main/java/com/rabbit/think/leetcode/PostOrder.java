package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;
/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * @author rabbit
 * @date   Sep 5, 2014
 */
public class PostOrder {
	
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> vals = new ArrayList<Integer>();
		traversal(vals, root);
		return vals;
    }
	
	private void traversal(List<Integer> vals, TreeNode node){
		if(node == null){
			return;
		}
		traversal(vals, node.left);
		traversal(vals, node.right);
		vals.add(node.val);
	}
	
	public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
}
