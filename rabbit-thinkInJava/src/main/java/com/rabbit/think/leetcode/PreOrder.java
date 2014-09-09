package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.rabbit.think.leetcode.SumTreeNumbers.TreeNode;
/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * @author rabbit
 * @date   Sep 5, 2014
 */
public class PreOrder {
	
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> vals = new ArrayList<Integer>();
		traversal(vals, root);
		return vals;
    }
    
    private void traversal(List<Integer> vals, TreeNode node){
		if(node == null){
			return;
		}
		vals.add(node.val);
		traversal(vals, node.left);
		traversal(vals, node.right);
	}
    
    /**
	 * 先序遍历二叉树，用栈
	 * @param root
	 */
	private List<Integer> preTraversal(TreeNode root){
		List<Integer> vals = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		while(p != null || !stack.isEmpty()){
			while(p != null){
				vals.add(p.val);
				stack.push(p);
				p = p.left;
			}
			if(!stack.isEmpty()){
				p = stack.pop();
				p = p.right;
			}
		}
		return vals;
	}
	
	public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
}
