package 목요빈.Week06;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {

	static int N, move;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = {0, 1, 1}, dc = {1, 0, 1};
	
	// 파이프 한쪽 끝을 (N,N)으로 이동시키는 방법의 개수
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		dfs(0, 1, 0);
		System.out.println(move);
	}
	
	// int status -> 0: 가로, 1: 세로, 2: 대각선
	static void dfs(int r, int c, int status) {
		 if(r == N-1 && c == N-1) {
			 move += 1;
			 return;
		 }
		 
		 switch(status) {
		 case 0: // 가로에서 갈 수 있는 방향: 가로, 대각선
			 move(r, c, new int[] {0, 2});
			 break;
		 case 1: // 세로에서 갈 수 있는 방향: 세로, 대각선
			 move(r, c, new int[] {1, 2});
			 break;
		 case 2: // 대각선에서 갈 수 있는 방향: 가로, 세로, 대각선
			 move(r, c, new int[] {0, 1, 2});
			 break;
		 }
	}
	
	// status 방향일 때 갈 수 있는 방향으로 갈 수 있는지..
	static boolean check(int r, int c, int status) {
		if(status != 2) {
			int nr = r + dr[status];
			int nc = c + dc[status];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == 0) return true;
			return false;
		}else {
			for (int d = 0; d < 3; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 1) return false;
			}
			return true;
		}
	}
	
	// 갈 수 있으면 가기
	static void move(int r, int c, int[] dir) {
		for (int d : dir) {
			if(check(r, c, d)) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(visited[nr][nc]) continue;
				visited[nr][nc] = true;
				dfs(nr, nc, d);
				visited[nr][nc] = false;
			 }
		 }
	}
}