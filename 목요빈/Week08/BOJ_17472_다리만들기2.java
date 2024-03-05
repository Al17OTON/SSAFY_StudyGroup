package 목요빈.Week08;

import java.io.*;
import java.util.*;

public class BOJ_17472_다리만들기2 {

	static int N, M;
	static int[][] board;
	static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1}, parent;
	static List<Edge> edgeList;
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static class Edge{
		int f, t, w;

		public Edge(int f, int t, int w) {
			super();
			this.f = f;
			this.t = t;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Edge [f=" + f + ", t=" + t + ", w=" + w + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); // <= 10
		// 섬의 개수 2 ~ 6
		
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		// 섬 번호 매기기
		int num = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == 1) {
					bfs(i, j, num);
					num += 1;
				}
			}
		}
		
		// 가능한 다리 모두 놓기
		edgeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] > 1) {
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
						if(board[nr][nc] == 0) {
							int[] b = bridge(nr, nc, d);
							if(b == null) continue;
							if(b[1] <= 1) continue;
							edgeList.add(new Edge(board[i][j], b[0], b[1]));
						}
					}
				}
			}
		}
		
		parent = new int[num-2];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		int c = 0;
		int result = 0;
		edgeList.sort((Edge e1, Edge e2) -> e1.w - e2.w);
		
		for (Edge cur : edgeList) {
			if(!union(cur.f-2, cur.t-2)) continue;
			result += cur.w;
			c += 1;
		}
		
		if(c != num-3) result = 0;
		System.out.println(result == 0 ? -1 : result);
		
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA != rootB) {
			parent[rootB] = rootA;
			return true;
		} 
		return false;
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		else return parent[n] = find(parent[n]);
	}
	static void bfs(int r, int c, int n) {
		Queue<Pos> que = new LinkedList<>();
		que.add(new Pos(r, c));
		board[r][c] = n;
		
		while(!que.isEmpty()) {
			Pos t = que.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] != 1) continue;
				
				board[nr][nc] = n;
				que.offer(new Pos(nr, nc));
			}
		}
	}
	
	static int[] bridge(int r, int c, int d) {
		int nr = r;
		int nc = c;
		int weight = 0;
		int to = 0;
		boolean flag = false;
		
		while(nr >= 0 && nr < N && nc >= 0 && nc < M) {
			if(board[nr][nc] >= 1) {
				flag = true;
				to = board[nr][nc];
				break;
			}
			weight += 1;
			nr += dr[d];
			nc += dc[d];
		}
		
		return flag ? new int[] {to, weight} : null;
	}
}
