package 목요빈.Week04.HW;

import java.io.*;
import java.util.*;


public class BOJ_14502_연구소 {

	static int N, M, max;
	static int[][] board, temp;
	static boolean[][] visited;
	static int[] dr = { 1, 0, -1, 0 }, dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		
		copy(board);
		dfs(0, 0, 0);
	}
	
	static void dfs(int n, int x, int y) {
		if(n == 3) {
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(temp[i]));
			}
			System.out.println();
			
			int[][] virus = copy(temp);
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(board[r][c] == 2){ 
						bfs(r, c, virus);
					}
				}
			}
			max = Math.max(max, count(virus));
		}
		
		for (int i = x; i < N; i++) {
			for (int j = y; j < M; j++) {
				if(temp[i][j] == 0) {
					temp[i][j] = 1;
					dfs(n+1, i, j+1);
					dfs(n+1, i+1, j);
					dfs(n+1, i+1, j+1);
					temp[i][j] = 0;
				}
			}
		}
	}
	
	static int count(int[][] board) {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(board[r][c] == 0){
					count += 1;
				}
			}
		}
		return count;
	}
	
	static void bfs(int r, int c, int[][] board) {
		Queue<Land> que = new ArrayDeque<>();
		visited = new boolean[N][M];
		visited[r][c] = true;
		que.add(new Land(r, c));
		
		while(!que.isEmpty()) {
			Land tmp = que.poll();
			for (int i = 0; i < 4; i++) {
				int nr = tmp.r + dr[i];
				int nc = tmp.c + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N 
						&& board[nr][nc] == 0) {
					board[nr][nc] = 2;
					que.add(new Land(nr, nc));
				}
			}
		}
	}
	
	static int[][] copy(int[][] array) {
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			temp[i] = array[i].clone();
		}
		return temp;
	}
}
