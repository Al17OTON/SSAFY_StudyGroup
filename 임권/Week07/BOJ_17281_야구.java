package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17281_야구 {
	
	static int N;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][9];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] order = new int[9];
		order[3] = 0;	//4번타자는 1번째 선수다.
		
		boolean[] v = new boolean[9];
		v[0] = true;
		
		System.out.println(dfs(order, 0, v));
	}

	//permutation? 순열?
	static int dfs(int[] sel, int idx, boolean[] v) {
		if(idx == sel.length) {
			ground = new boolean[3];
			return play(sel, 0, 0, 0, 0);
		}
		
		//4번 타자는 1번 선수로 정해졌으므로 스킵
		if(idx == 3) return dfs(sel, idx + 1, v);
		
		int score = -1;
		//1번 선수는 4번 타자이므로 2번 선수부터 시작한다.
		for(int i = 1; i < 9; i++) {
			if(!v[i]) {
				v[i] = true;
				sel[idx] = i;
				score = Math.max(dfs(sel, idx + 1, v), score);
				v[i] = false;
			}
		}
		return score;
	}
	
	static boolean[] ground;
	
	static int play(int[] sel, int n, int idx, int out, int score) {
		if(n == N) {
			return score;
		}
		//타순의 사이클
		if(idx == 9) idx = 0;
		
		int exp = arr[n][sel[idx]];
		//아웃 당하는 경우
		if(exp == 0) {
			//삼진아웃으로 인해 이닝이 끝남
			if(out + 1 == 3) {
				ground = new boolean[3];
				return play(sel, n + 1, idx + 1, 0, score);
			}
			return play(sel, n, idx + 1, out + 1, score);
		}
		
		//주자들을 진루한다.
		for(int i = ground.length - 1; i >= 0; i--) {
			if(ground[i]) {
				if(i + exp >= ground.length) score++;
				else {
					ground[i + exp] = true;
				}
				ground[i] = false;
			}	
		}
		//타자를 위치시킨다.
		if(exp - 1 >= ground.length) score++;	//홈런인 경우
		else ground[exp - 1] = true;
		
		return play(sel, n, idx + 1, out, score);
	}
}


















