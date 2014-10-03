package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 
 * @author rabbit
 * @date   Oct 3, 2014
 */
public class BinaryTreeLevelOrderTraversal {

	public Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        DFS(root, 1);
        for(int i=1; i<=map.keySet().size(); i++){
            lists.add(map.get(i));
        }
        return lists;
    }
    
    public void DFS(TreeNode node, int level){
        if(node != null){
            if(map.containsKey(level)){
                map.get(level).add(node.val);
            }else{
                List<Integer> list = new ArrayList<Integer>();
                list.add(node.val);
                map.put(level, list);
            }
            DFS(node.left, level + 1);
            DFS(node.right, level + 1);
        }
    }
    
    //Definition for binary tree
  	public class TreeNode {
  		int val;
  		TreeNode left;
  		TreeNode right;
  		TreeNode(int x) { val = x; }
  	}
}
