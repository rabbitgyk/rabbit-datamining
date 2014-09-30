package com.rabbit.think.leetcode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * @author rabbit
 * @date   Sep 30, 2014
 */
public class ConstructBinaryTreeInPost {

	public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || 
            inorder.length == 0 || postorder.length == 0){
            return null;
        }
        
        return buildDFS(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    //递归建树
    public TreeNode buildDFS(int[] inorder, int low1, int high1, int[] postorder, int low2, int high2){
        if(low1 > high1 || low2 > high2){
            return null;
        }
        int val = postorder[high2];
        TreeNode tree = new TreeNode(val);
        int valIndex = low1;
        for(int i=low1; i<=high1; i++){
            if(inorder[i] == val){
                valIndex = i;
                break;
            }
        }
        tree.left = buildDFS(inorder, low1, valIndex-1, postorder, low2, low2+(valIndex-low1-1));
        tree.right = buildDFS(inorder, valIndex+1, high1, postorder, low2+valIndex-low1, high2-1);
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
