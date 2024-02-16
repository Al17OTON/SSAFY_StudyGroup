package 목요빈.Week03;

import java.util.*;

public class BOJ_2447_별찍기10 {

	static String[][] board;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		board = new String[N][N];
		
		star(0, 0, false, N);
		
		for (String[] arr : board) {
			for (String str : arr) {
				sb.append(str);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		sc.close();
	}

	private static void star(int r, int c, boolean blank, int N) {
		if(blank) {
			for(int i = r; i < r + N; i++) {
				for (int j = c; j < c + N; j++) {
					board[i][j] = " ";
				}
			}
			return;
		}
		
		if(N == 1) {
			board[r][c] = "*";
			return;
		}
		
		int step = N / 3;
		int count = 0;
		for(int i = r; i < r + N; i += step) {
			for (int j = c; j < c + N; j += step) {
				count += 1;
				if(count == 5) // 가운데 공백
					star(i,  j, true, step);
				else
					star(i, j, false, step);
			}
		}
	}
}
