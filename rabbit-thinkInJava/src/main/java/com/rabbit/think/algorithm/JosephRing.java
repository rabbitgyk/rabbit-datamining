package com.rabbit.think.algorithm;

public class JosephRing {
	
	public static void main(String[] args) {
		Node head = new Node(0, false);
		Node p = head;
		for(int i=1; i<=30; i++){
			Node node = new Node(i, true);
			p.next = node;
			p = p.next;
		}
		p.next = head.next;
		
		int[] ps = throwNodes(head.next, 15, 9);
		for(int no : ps){
			System.out.print(no+" ");
		}
	}
	
	/**
	 * 每次扔掉第nth个node，总共扔掉num个
	 * @param head
	 * @param num
	 * @param nth
	 * @return
	 */
	public static int[] throwNodes(Node head, int num, int nth){
		int[] nodes = new int[num];
		Node p = head;
		int count = 0;
		int n = 0;
		while(count < num){
			n++;
			if(n == nth){
				p.flag = false;
				nodes[count] = p.data;
				count++;
				n = 0;
			}
			p = p.next;
			while(p.flag == false){
				p = p.next;
			}
		}
		
		return nodes;
	}
	
	static class Node{
		int data;
		boolean flag;
		Node next;
		Node(int d, boolean f){
			data = d;
			flag = f;
		}
	}
}
