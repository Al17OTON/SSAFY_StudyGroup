package Week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_6109_추억의2048게임 {
	
	static int N;
	static int[][] map;
	static boolean[][] v;
	static int[] dirArr = {-1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String com = st.nextToken();
			map = new int[N][N];
			v = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			switch(com) {
			case "up" :
				up();
				break;
			case "down" :
				down();
				break;
			case "right" :
				right();
				break;
			case "left" :
				left();
				break;
			}
			print(t);
		}
	}
	
	static void up() {
		int rr, tmp, dir = dirArr[0];
		for(int c = 0; c < N; c++) {
			for(int r = 0; r < N; r++) {
				if(map[r][c] != 0) {
					rr = r + dir;
					while(rr >= 0 && map[rr][c] == 0) rr += dir;
					if(rr <= -1) {			//벽에 부딫힌경우
						tmp = map[r][c];
						map[r][c] = 0;
						map[0][c] = tmp;
					} else {
						if(map[rr][c] == map[r][c] && !v[rr][c]) {	//합쳐진 경우
							map[rr][c] *= 2;
							map[r][c] = 0;
							v[rr][c] = true;
						} else {			//합쳐지지 못한 경우
							tmp = map[r][c];
							map[r][c] = 0;
							map[rr - dir][c] = tmp;
						}
					}
				}
			}
		}
	}
	
	static void down() {
		int rr, tmp, dir = dirArr[1];
		for(int c = 0; c < N; c++) {
			for(int r = N - 1; r >= 0; r--) {
				if(map[r][c] != 0) {
					rr = r + dir;
					while(rr < N && map[rr][c] == 0) rr += dir;
					if(rr >= N) {
						tmp = map[r][c];
						map[r][c] = 0;
						map[N - 1][c] = tmp;
					} else {
						if(map[rr][c] == map[r][c] && !v[rr][c]) {
							map[rr][c] *= 2;
							map[r][c] = 0;
							v[rr][c] = true;
						} else {
							tmp = map[r][c];
							map[r][c] = 0;
							map[rr - dir][c] = tmp;
						}
					}
				}
			}
		}
	}
	
	static void left() {
		int cc, tmp, dir = dirArr[0];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] != 0) {
					cc = c + dir;
					while(cc >= 0 && map[r][cc] == 0) cc += dir;
					if(cc <= -1) {
						tmp = map[r][c];
						map[r][c] = 0;
						map[r][0] = tmp;
					} else {
						if(map[r][cc] == map[r][c] && !v[r][cc]) {
							map[r][cc] *= 2;
							map[r][c] = 0;
							v[r][cc] = true;
						} else {
							tmp = map[r][c];
							map[r][c] = 0;
							map[r][cc - dir] = tmp;
						}
					}
				}
			}
		}
	}
	
	static void right() {
		int cc, tmp, dir = dirArr[1];
		for(int r = 0; r < N; r++) {
			for(int c = N - 1; c >= 0; c--) {
				if(map[r][c] != 0) {
					cc = c + dir;
					while(cc < N && map[r][cc] == 0) cc += dir;
					if(cc >= N) {
						tmp = map[r][c];
						map[r][c] = 0;
						map[r][N - 1] = tmp;
					} else {
						if(map[r][cc] == map[r][c] && !v[r][cc]) {
							map[r][cc] *= 2;
							map[r][c] = 0;
							v[r][cc] = true;
						} else {
							tmp = map[r][c];
							map[r][c] = 0;
							map[r][cc - dir] = tmp;
						}
					}
				}
			}
		}
	}
	
	static void print(int t) {
		StringBuilder sb = new StringBuilder();
		sb.append("#" + t + "\n");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}

