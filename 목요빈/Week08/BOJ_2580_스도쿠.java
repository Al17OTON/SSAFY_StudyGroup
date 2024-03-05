package 목요빈.Week08;

import java.io.*;
import java.util.*;

public class BOJ_2580_스도쿠 {

	static int[][] board;
	static List<Pos> hole;
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[9][9];
		hole = new ArrayList<>();
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) hole.add(new Pos(i, j));
			}
		}
		
		dfs(0);
	}
	
	static void dfs(int n) {
		if(n == hole.size()) {
			for (int[] a : board) {
				for (int num : a) {
					System.out.print(num + " ");
				}
				System.out.println();
			}
			System.out.println();
			System.exit(0);
			return;
		}
		
		Pos cur = hole.get(n);
		for (int i = 1; i <= 9; i++) {
			// 1~9까지 다 넣어보기 -> 같은 수가 range에 있는지 확인
			if(check(cur.r, cur.c, i)) {
				board[cur.r][cur.c] = i;
				dfs(n+1);
                board[cur.r][cur.c] = 0;
			}
		}
	}
	
	static boolean check(int r, int c, int num) {
		// r
		boolean flag = true;
		for (int i = 0; i < 9; i++) {
			if(board[r][i] == num) flag = false;
		}
		
		// c
		for (int i = 0; i < 9; i++) {
			if(board[i][c] == num) flag =  false;
		}
		
		// square
		r = r/3 * 3;
		c = c/3 * 3;
		for (int i = r; i < r+3; i++) {
			for (int j = c; j < c+3; j++) {
				if(board[i][j] == num) flag = false;
			}
		}
		
		return flag;
	}
}
