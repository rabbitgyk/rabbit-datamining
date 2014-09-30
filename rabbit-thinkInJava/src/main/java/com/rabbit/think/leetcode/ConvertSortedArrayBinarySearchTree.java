package com.rabbit.think.leetcode;

/**
 * Given an array where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 * 
 * @author rabbit
 * @date   Sep 30, 2014
 */
public class ConvertSortedArrayBinarySearchTree {
	
	public TreeNode sortedArrayToBST(int[] num) {
        if(num == null || num.length == 0){
            return null;
        }
        return toBST(num, 0, num.length-1);
    }
    
    public TreeNode toBST(int[] num, int low, int high){
        if(low > high){
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode tree = new TreeNode(num[mid]);
        tree.left = toBST(num, low, mid-1);
        tree.right = toBST(num, mid+1, high);
        return tree;
    }
	
	//Definition for binary tree
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
