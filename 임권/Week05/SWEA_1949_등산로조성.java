package Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {

	static int N, K;
	static int[][] map;
	static boolean[][] v;
	static int len;
	static int[] dirX = {-1, 1, 0, 0};
	static int[] dirY = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			int max = 0;
			len = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			v = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == max) {
						v[i][j] = true;
						dfs(i, j, 1, true);
						v[i][j] = false;
					}
				}
			}
			
			System.out.println("#" + t + " " + len);
		}
	}
	
	static void dfs(int i, int j, int depth, boolean isK) {
		len = Math.max(len, depth);
		
		for(int d = 0; d < dirX.length; d++) {
			int ii = i + dirX[d], jj = j + dirY[d];
			
			if(ii >= N || ii < 0 || jj >= N || jj < 0 || v[ii][jj]) continue;
			if(map[ii][jj] < map[i][j]) {
				v[ii][jj] = true;
				dfs(ii, jj, depth + 1, isK);
				v[ii][jj] = false;
			} else if(isK && map[ii][jj] - K < map[i][j]) {
				int tmp = map[ii][jj];
				map[ii][jj] = map[i][j] - 1;
				v[ii][jj] = true;
				dfs(ii, jj, depth + 1, false);
				map[ii][jj] = tmp;
				v[ii][jj] = false;
			}
		}
	}
}

















