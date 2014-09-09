package com.rabbit.think.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 * 
 * @author rabbit
 * @date   Sep 9, 2014
 */
public class CloneGraph {
	
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null)
			return null;
		
		HashMap<Integer, UndirectedGraphNode> copyRecord = new HashMap<Integer, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.offer(node);
		
		while(!queue.isEmpty()){
			UndirectedGraphNode curr = queue.poll();
			UndirectedGraphNode newNode = copyRecord.get(curr.label);
			if(newNode == null){
				newNode = new UndirectedGraphNode(curr.label);
				copyRecord.put(newNode.label, newNode);
			}
			for(UndirectedGraphNode neighbor : curr.neighbors){
				UndirectedGraphNode newNeighbor = copyRecord.get(neighbor.label);
				if(newNeighbor == null){
					newNeighbor = new UndirectedGraphNode(neighbor.label);
					copyRecord.put(newNeighbor.label, newNeighbor);
					queue.offer(neighbor);
				}
				newNode.neighbors.add(newNeighbor);
			}
		}
		
		return copyRecord.get(node.label);
    }
	
	static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		public UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
}
}
