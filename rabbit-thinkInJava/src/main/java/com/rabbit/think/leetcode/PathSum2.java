package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * return
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * 
 * @author rabbit
 * @date   Sep 17, 2014
 */
public class PathSum2 {

	List<List<Integer>> lists = new ArrayList<List<Integer>>();
	Stack<Integer> stack = new Stack<Integer>();
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        pathSumHelper(root, sum);
		return lists;
    }
	
	private void pathSumHelper(TreeNode root, int sum){
	    if(root == null){
        	return;
        }
		stack.push(root.val);
        if(root.left == null && root.right == null){
        	if(sum == root.val){
        		List<Integer> list = new ArrayList<Integer>(stack);
        		lists.add(list);
            }
        }
        
        sum = sum -root.val;
        if(root.left != null){
        	pathSumHelper(root.left, sum);
        }
        if(root.right != null){
        	pathSumHelper(root.right, sum);
        }
        stack.pop();
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
