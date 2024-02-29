import java.io.*;
import java.util.*;

public class BOJ_14500_테트로미노 {

	static int N, M, max;
	static int[][] board;
	
// 브루트포스
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // <= 500 ㄷㄷ

		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				rec(i, j);
			}
		}
		
		System.out.println(max);
	}
	
	static void rec(int r, int c) {
		// 가로
		int sum = 0;
		int temp = 0;

		if(r+2 <= N && c+3 <= M) {
			for (int i = r; i < r+2; i++) {
				for (int j = c; j < c+3; j++) {
					sum += board[i][j];
				}
			}
			
			for (int[][] cases : blank) {
				temp = sum;
				for (int[] pos : cases) { // ex. {{0, 0}, {0, 1}}
					temp -= board[r+pos[0]][c+pos[1]];
				}
				max = Math.max(temp, max);
			}
		}
		
		// 세로
		sum = temp = 0;
		if(r+3 <= N && c+2 <= M) {
			for (int i = r; i < r+3; i++) {
				for (int j = c; j < c+2; j++) {
					sum += board[i][j];
				}
			}

			for (int[][] cases : blank) {
				temp = sum;
				for (int[] pos : cases) { // ex. {{0, 0}, {0, 1}}
					temp -= board[r+pos[1]][c+pos[0]];
				}
				max = Math.max(temp, max);
			}
		}
		
		
		// 1번 조각 가로
		sum = 0;
		if(r+4 <= N) {
			for (int i = r; i < r+4; i++) {
				sum += board[i][c];
			}
			max = Math.max(sum, max);
		}
		
		// 1번 조각 세로
		sum = 0;
		if(c+4 <= M) {
			for (int i = c; i < c+4; i++) {
				sum += board[r][i];
			}
			max = Math.max(sum, max);		
		}
		
		// 2번 조각
		sum = 0;
		if(r+2 <= N && c+2 <= M) {
			for (int i = r; i < r+2; i++) {
				for (int j = c; j < c+2; j++) {
					sum += board[i][j];
				}
			}
			max = Math.max(sum, max);		
		}
	}

	// blank[경우][n번째 칸][x, y];
	static int[][][] blank = {{{0, 0}, {0, 1}}, {{0, 1}, {0, 2}}, 
			{{1, 0}, {1, 1}}, {{1, 1}, {1, 2}}, {{0, 0}, {1, 2}},
			{{0, 2}, {1, 0}}, {{0, 0}, {0, 2}}, {{1, 0}, {1, 2}}};
}
