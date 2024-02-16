package 목요빈.Week05;

import java.io.*;
import java.util.*;

public class SWEA_1767_프로세서연결하기 {
	
	/*
	 * 프로세서 연결하기 1개의 셀에는 core 또는 전선이 올 수 있음 - 전선은 직선으로만 가능 + 교차 불가 - 가장자리에 위치한 core는
	 * 이미 전원이 연결됨 Q) 최대한 많은 core에 전원을 연결했을 경우 전선 길이의 값 중 최소 최대한 많은 core에 전원을 연결해도
	 * 전원이 연결되지 않는 core가 존재할 수 있음
	 */
	
	// 12시부터 시계방향
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
	static int N, maxCore, minLen;
	static int[][] board;
	static List<Core> cores;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 7 ~ 12
			
			board = new int[N][N];
			cores = new LinkedList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] != 1) continue;
					if(i != 0 && i != N-1 && j != 0 && j != N-1) {
						cores.add(new Core(i, j));
					}
				}
			}
			
			maxCore = 0;
			minLen = Integer.MAX_VALUE;
			
			recursive(0, 0, 0);
			System.out.printf("#%d %d\n", t, minLen);
		}
	}

	static void recursive(int n, int core, int len) {
		if(n == cores.size()) {
			if(maxCore < core) {
				maxCore = core;
				minLen = len;
			}else if(maxCore == core) {
				minLen = Math.min(minLen, len);
			}
			return;
		}
		
		Core cur = cores.get(n);
		for (int i = 0; i < 4; i++) {
			int nr = cur.r + dr[i];
			int nc = cur.c + dc[i];
			boolean flag = true; // 이 방향으로 전선 이을 수 있음
			int count = 0;
			
			while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if(board[nr][nc] != 0) {
					flag = false;
					break;
				}
				board[nr][nc] = 2;
				count += 1;
				nr += dr[i];
				nc += dc[i];
			}
			
			if(flag) recursive(n+1, core+1, len+count);
			else recursive(n+1, core, len);
			
			for (int j = 0; j < count; j++) {
				nr -= dr[i];
				nc -= dc[i];
				board[nr][nc] = 0;
			}
		}
	}

}

class Core {
	int r, c, dir[];

	public Core(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}