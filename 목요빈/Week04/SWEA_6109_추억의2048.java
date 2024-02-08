package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_6109_추억의2048 {
	
	static int N;
	static Stack<Tile> stk;
	static int[][] board, result;
	
	// 2048
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // <= 20
			String action = st.nextToken();
			
			board = new int[N][N];
			result = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			stk = new Stack<>();
			switch(action) {
			case "up":
				moveUp();
				break;
			case "down":
				moveDown();
				break;
			case "right":
				moveRight();
				break;
			case "left":
				moveLeft();
				break;
			}
			
			sb.append("#" + t  + "\n");
			for(int i = 0; i<N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(result[i][j] + " ");
				}
				if(i != N-1) sb.append("\n");
			}
			System.out.println(sb);
		}
	}
	
	static void move(int r, int c) {
		if(board[r][c] == 0) return;
		if(stk.isEmpty()) stk.push(new Tile(board[r][c], false));
		else {
			Tile tmp = stk.pop();
			
			if(tmp.n == board[r][c] && !tmp.isMerge) stk.push(new Tile(2 * tmp.n, true));
			else {
				stk.push(tmp);
				stk.push(new Tile(board[r][c], false));
			}
		}
	}
	
	static void moveUp() {
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				move(r, c);
			}
			int size = stk.size()-1;
			while(!stk.isEmpty()) {
				result[size--][c] = stk.pop().n;
			}
		}
	}
	
	static void moveRight() {
		for (int r = 0; r < N; r++) {
			for (int c = N-1; c >= 0; c--) {
				move(r, c);
			}
			
			int size = N-stk.size();
			while(!stk.isEmpty()) {
				result[r][size++] = stk.pop().n;
			}
		}
	}
	
	static void moveDown() {
		for (int c = 0; c < N; c++) {
			for (int r = N-1; r >= 0; r--) {
				move(r, c);
			}
			int start = N-stk.size();
			while(!stk.isEmpty()) {
				result[start++][c] = stk.pop().n;
			}
		}
	}
	
	static void moveLeft() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				move(r, c);
			}
			int size = stk.size()-1;
			while(!stk.isEmpty()) {
				result[r][size--] = stk.pop().n;
			}
		}
	}
}

class Tile{
	int n;
	boolean isMerge;
	
	public Tile(int n, boolean isMerge) {
		super();
		this.n = n;
		this.isMerge = isMerge;
	}
}
