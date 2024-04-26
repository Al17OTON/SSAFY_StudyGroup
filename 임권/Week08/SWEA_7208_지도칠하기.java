package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7208_지도칠하기 {	
	static int N;
	static boolean[][] adj;
	static int[] colorArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			adj = new boolean[N][N];
			colorArr = new int[N];
			
			for(int i = 0; i < N; i++) {
				colorArr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					if(st.nextToken().charAt(0) == '1') adj[r][c] = true;
				}
			}
			
			int i;
			boolean flag = true;
			
			L : for(int a = 0; a < N; a++) {
				for(int b = 0; b < N; b++) {
					if(adj[a][b] && colorArr[a] == colorArr[b]) {
						flag = false;
						break L;
					}
				}
			}
			
			if(flag) i = 0;
			else {
				for(i = 1; i <= N; i++) {
					if(dfs(0, i, colorArr.clone())) break;
				}
			}
			
			System.out.println("#" + t + " " + i);
		}
	}
	
	static boolean dfs(int idx, int count, int[] color) {
		if(idx == N) {
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(adj[i][j] && color[i] == color[j]) return false;
				}
			}
			
			return true;
		}
		
		for(int c = 1; c < 5; c++) {
			int tmp = color[idx];
			if(tmp == c) {
				if(dfs(idx + 1, count, color.clone())) return true;
			} else if (count > 0){
				color[idx] = c;
				if(dfs(idx + 1, count - 1, color.clone())) return true;
				color[idx] = tmp;
			}
		}
		
		
		return false;
	}
	
}



















