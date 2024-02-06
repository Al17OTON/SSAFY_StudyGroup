package 목요빈.Week03;

import java.io.*;
import java.util.*;

public class BOJ_16234_인구이동 {

	static int N, L, R, num;
	static int[][] board, union;
	static int[][] visited;
	static int[] info, dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			
			union = new int[N][N];
			info = new int[N*N/2];
			visited = new int[N][N];
			num = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] <= 4) {
						dfs(i, j);
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(union[i][j] != 0) {
						board[i][j] = info[union[i][j]];
					}
				}
			}
			if(num == 1) break;
			count += 1;
		}
		
		
		System.out.println(count);
	}
	
	static void dfs(int x, int y) {
		Queue<Pos> que = new ArrayDeque<>();
		int sum = 0, count = 0;
		que.add(new Pos(x, y));
		visited[x][y] = 1;
		
		while(!que.isEmpty()) {
			Pos temp = que.poll();
			int r = temp.r, c = temp.c;
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] <= 4) {
					if(union[nr][nc] != 0) continue;
					int dif = Math.abs(board[r][c] - board[nr][nc]);
					if(dif >= L && dif <= R) {
						union[nr][nc] = num;
						visited[nr][nc] += 1;
						sum += board[nr][nc];
						count += 1;
						que.add(new Pos(nr, nc));
					}
				}
			}
		}

		if(count != 0) {
			union[x][y] = num;
			info[num] = sum / count;
			num += 1;
		}
	}
}

class Pos {
	int r, c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
