package 목요빈.Week08;

import java.io.*;
import java.util.*;

public class SWEA_7208_지도칠하기2 {

	static int N, min, color[], select[];
	static int[][] graph;
	
	// 색상을 최소로 변경해 모든 나라의 색 지정
	// 인접한 정점 색 같은지 확인
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // <= 8
			color = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}
		
			graph = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			select = new int[N];
			dfs(0);
			System.out.printf("#%d %d\n", t, min);
			
		}
	}

	static boolean adjCheck() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(graph[i][j] == 0) continue;
				if(select[i] == select[j]) return false;
			}
		}
		return true;
	}
	// 완탐?
	static void dfs(int n) {
		if(n == N) {
			if(adjCheck()) {
				int cnt = 0;
				for (int i = 0; i < N; i++) {
					if(select[i] != color[i]) cnt += 1;
				}
				min = Math.min(cnt, min);
			}
			return;
		}
		
		for (int i = 1; i <= 4; i++) {
			select[n] = i;
			dfs(n+1);
			select[n] = 0;
		}
	}
}
