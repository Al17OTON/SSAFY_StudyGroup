package 목요빈.Week06;

import java.io.*;
import java.util.*;

public class BOJ_17281_야구 {
	static int N, max;
	static int[] player, base;
	static int[][] inning;
	static boolean[] selected;
	
	// 타순과 득점을 구해라
	// 8! 정도는 ㄱㅊ으려나? -> 아 무조건 시초임 아닌가! 모르겠다!
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 이닝 수 <= 50
		inning = new int[N][9]; // 각 선수가 각 이닝에서 얻는 결과
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		base = new int[3];
		player = new int[8];
		selected = new boolean[8];
		perm(0);
		System.out.println(max);
	}
	
	static void play(int[] order){
		int score = 0;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int[] base = new int[3];
			int out = 0;
			while(out < 3) {
				idx %= 9;
				int result;
				if(idx == 3) result = inning[i][0]; // 4번 타자 -> inning[i][0] : 1번 타자 지명
				else if(idx < 3) result = inning[i][order[idx]];// j번째 선수의 결과 
				else result = inning[i][order[idx-1]];
				
				switch(result) { 
				case 0: // 아웃
					out += 1;
					break;
				case 1: // 안타
					score += base[2];
					base[2] = base[1];
					base[1] = base[0];
					base[0] = 1;
					break;
				case 2: // 2루타
					score += base[2] + base[1];
					base[2] = base[0];
					base[1] = 1;
					base[0] = 0;
					break;
				case 3: // 3루타
					score += base[0] + base[1] + base[2];
					base[0] = base[1] = 0;
					base[2] = 1;
					break;
				case 4: // 홈런
					score += base[0] + base[1] + base[2] + 1;
					Arrays.fill(base, 0);
					break;
				}

				idx += 1;
			}
		}
		max = Math.max(score, max);
	}
	
	static void perm(int n) {
		if(n == 8) {
			play(player);
			return;
		}
		
		for (int i = 1; i <= 8; i++) {
			if(selected[i-1]) continue;
			selected[i-1] = true;
			player[n] = i;
			perm(n+1);
			selected[i-1] = false;
		}
	}
}
