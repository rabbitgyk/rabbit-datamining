package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;
//import java.util.Stack;

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
    
//    /**
//	 * 先序遍历二叉树，用栈
//	 * @param root
//	 */
//	private List<Integer> preTraversal(TreeNode root){
//		List<Integer> vals = new ArrayList<Integer>();
//        if(root == null){
//            return vals;
//        }
//		Stack<TreeNode> stack = new Stack<TreeNode>();
//		stack.push(root);
//		while(!stack.isEmpty()){
//			TreeNode node = stack.pop();
//			vals.add(node.val);
//			if(node.right != null){
//			    stack.push(node.right);
//			}
//			if(node.left != null){
//			    stack.push(node.left);
//			}
//		}
//		return vals;
//	}
	
	public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
}
