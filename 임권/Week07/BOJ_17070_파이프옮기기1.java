package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	static int N, count = 0;
	static boolean[][] map;
	
	static int[] dr = {0, 1, 1};	//우, 하, 우하
	static int[] dc = {1, 0, 1};
	static boolean[][] can = {
		{true, false, true},	//가로(우)상태는 우, 우하만 이동가능 
		{false, true, true},	//세로(하)상태는 하, 우하만 가능
		{true, true, true}		//대각선(우하)상태는 모든 방향이 이동가능하다.
	};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				if(st.nextToken().charAt(0) == '1' ) map[r][c] = true;
			}
		}
		
		dfs(0, 1, 0);
		
		System.out.println(count);
	}
	
	static void dfs(int r, int c, int dir) {
		if(r == N - 1 && c == N - 1) {
			count++;
			return;
		}
		
		for(int i = 0; i < can[dir].length; i++) {
			if(can[dir][i]) {
				if(i == 2 && check(r, c)) {
					dfs(r + 1, c + 1, i);
				} else if(i == 0 || i == 1) {
					int rr = r + dr[i];
					int cc = c + dc[i];
					if(rr < N && cc < N && !map[rr][cc]) {
						dfs(rr, cc, i);
					}
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		int rr = r + 1, cc = c + 1;
		if(rr >= N || cc >= N) return false;
		if(map[r][cc] || map[rr][c] || map[rr][cc]) return false;
		return true;
	}
}













