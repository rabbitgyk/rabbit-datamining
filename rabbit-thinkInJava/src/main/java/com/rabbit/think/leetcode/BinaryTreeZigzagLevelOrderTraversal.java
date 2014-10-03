package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * 
 * @author rabbit
 * @date   Oct 3, 2014
 */
public class BinaryTreeZigzagLevelOrderTraversal {

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if(root == null){
            return lists;
        }
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            if(!stack1.isEmpty()){
                List<Integer> list = new ArrayList<Integer>(stack1.size());
                while(!stack1.isEmpty()){
                    TreeNode node = stack1.pop();
                    list.add(node.val);
                    if(node.left != null)
                        stack2.push(node.left);
                    if(node.right != null)
                        stack2.push(node.right);
                }
                lists.add(list);
            }else if(!stack2.isEmpty()){
                List<Integer> list = new ArrayList<Integer>(stack2.size());
                while(!stack2.isEmpty()){
                    TreeNode node = stack2.pop();
                    list.add(node.val);
                    if(node.right != null)
                        stack1.push(node.right);
                    if(node.left != null)
                        stack1.push(node.left);
                }
                lists.add(list);
            }
        }
        return lists;
    }
	
	//Definition for binary tree
  	public class TreeNode {
  		int val;
  		TreeNode left;
  		TreeNode right;
  		TreeNode(int x) { val = x; }
  	}
}
