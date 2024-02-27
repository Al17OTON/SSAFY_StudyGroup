package 목요빈.Week07;

import java.io.*;
import java.util.*;


public class BOJ_17136_색종이붙이기 {

	static List<Pos> list;
	static int[][] board;
	static int min, color[];
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	// 1이 적힌 모든 칸을 붙이는데 필요한 색종이 최소 개수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[10][10];
		list = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) list.add(new Pos(i, j));
			}
		}
		
		color = new int[6];
		Arrays.fill(color, 5);
		min = Integer.MAX_VALUE;
		solve(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	
	static void solve(int idx, int cnt) {
		if(idx == list.size()){
			min = Math.min(min, cnt);
			return;
		}
		
		Pos cur = list.get(idx);
		if(board[cur.r][cur.c] == 0) {
			solve(idx+1, cnt);
			return; ////////////////// 
		}
		
		if(cnt >= min) return; ////////// 
		
		for(int size = 1; size <= 5; size++) {
			if(color[size] == 0) continue;
			if(isAttach(cur.r, cur.c, size)) { ////////// 
				attach(cur.r, cur.c, size, 0);
				color[size] -= 1;
				solve(idx + 1, cnt + 1);
				// 돌아오면 원상복구
				color[size] += 1;
				attach(cur.r, cur.c, size, 1);
			}
		}
	}
	
	static boolean isAttach(int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if(i >= 10 || j >= 10) return false;
				if(board[i][j] != 1) return false;
			}
		}
		return true;
	}
	
	static void attach(int r, int c, int size, int color) {
		// 색종이 붙이기
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				board[i][j] = color;
			}
		}
	}
}
