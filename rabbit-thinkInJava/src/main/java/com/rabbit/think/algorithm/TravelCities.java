package com.rabbit.think.algorithm;
/**
 * Amzon校园招聘题目，解答不完整
 * @author rabbit
 * @date   Sep 27, 2014
 */
public class TravelCities {
	
	public static void main(String[] args) {
		int[][] cities = {{0,1,0},{0,0,0},{0,2,0}};
		TravelCities tc = new TravelCities();
		System.out.println(tc.pathNum(cities));
	}
	
	//1和2的位置
	int x1 = 0;
	int y1 = 0;
	int x2 = 0;
	int y2 = 0;
	int num = 0;
	int visitedNum = 0;
	
	public int pathNum(int[][] cities){
		int n = cities.length;
		int m = cities[0].length;
		
		//1.找到1和2的位置
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(cities[i][j] == 1){
					x1 = i;
					y1 = j;
				}else if(cities[i][j] == 2){
					x2 = i;
					y2 = j;
				}
			}
		}
		
		//2.遍历找路径数
		boolean[][] visited = new boolean[n][m];
		
		dfs(cities, x1, y1, visited);
		System.out.println(visitedNum);
		return num;
	}
	
	//深搜
	public int dfs(int[][] cities, int startX, int startY, boolean[][] visited){
		int n = cities.length;
		int m = cities[0].length;
		
		visited[startX][startY] = true;
		
		if(visited[x2][y2]){
			num++;
			visited[x2][y2] = false;
		}
		visitedNum++;
		if(startX - 1 > 0 && !visited[startX - 1][startY] && visitedNum <=16){
			dfs(cities, startX - 1, startY, visited);
		}
		if(startX - 1 > 0 && startY -1 > 0 && !visited[startX - 1][startY - 1] && visitedNum <=16){
			dfs(cities, startX - 1, startY - 1, visited);
		}
		if(startX - 1 > 0 && startY + 1 < m && !visited[startX - 1][startY + 1] && visitedNum <=16){
			dfs(cities, startX - 1, startY + 1, visited);
		}
		if(startY - 1 > 0 && !visited[startX][startY - 1] && visitedNum <=16){
			dfs(cities, startX, startY - 1, visited);
		}
		if(startY + 1 < m && !visited[startX][startY + 1] && visitedNum <=16){
			dfs(cities, startX, startY + 1, visited);
		}
		if(startX + 1 < n && !visited[startX + 1][startY] && visitedNum <=16){
			dfs(cities, startX + 1, startY, visited);
		}
		if(startX + 1 < n && startY - 1 > 0 && !visited[startX + 1][startY - 1] && visitedNum <=16){
			dfs(cities, startX + 1, startY - 1, visited);
		}
		if(startX + 1 < n && startY + 1 < m && !visited[startX + 1][startY + 1] && visitedNum <=16){
			dfs(cities, startX + 1, startY + 1, visited);
		}
		
		return num;
	}

}
