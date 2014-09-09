package com.rabbit.think.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * @author rabbit
 * @date   Sep 9, 2014
 */
public class SurroundedRegions {

	/**
	 * 解题思路：从边上的O开始便利，所有与边上O相连的O都不能变成X（可暂时变为Y），
	 * 之后再重新遍历一遍矩阵把所有O变成X，所有Y变成O。
	 * 需要注意的是，本题使用DFS也会出现栈溢出，只能使用BFS。从队列中取出一个节点，
	 * 把该节点变为Y，如果该节点的四周有还未变为Y的O，则这些节点放入队列。
	 * 
	 * @param board
	 */
	public void solve(char[][] board) {
		if(board == null || board.length == 0)
			return;
		
		Queue<Point> queue = new LinkedList<Point>();
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(i == 0 || j == 0 || i == (board.length-1) || j == (board[i].length-1)){
					if(board[i][j] == 'O'){
						Point p = new Point(i, j);
						queue.offer(p);
					}
				}
			}
		}
		
		while(!queue.isEmpty()){
			Point tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			if(board[x][y] == 'Y')
				continue;
			board[x][y] = 'Y';
			int x1 = x - 1;
			if(x1 >= 0 && board[x1][y] == 'O'){
				queue.offer(new Point(x1, y));
			}
			int x2 = x + 1;
			if(x2 < board.length && board[x2][y] == 'O'){
				queue.offer(new Point(x2, y));
			}
			int y1 = y - 1;
			if(y1 >= 0 && board[x][y1] == 'O'){
				queue.offer(new Point(x, y1));
			}
			int y2 = y + 1;
			if(y2 < board[0].length && board[x][y2] == 'O'){
				queue.offer(new Point(x, y2));
			}
		}
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j] == 'O')
					board[i][j] = 'X';
				else if(board[i][j] == 'Y')
					board[i][j] = 'O';
			}
		}
		
    }
	
	class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
