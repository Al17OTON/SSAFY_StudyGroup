package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2580_스도쿠 {
    static class Zero {
		int r, c;

		public Zero(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean[][] row = new boolean[9][10];
	static boolean[][] col = new boolean[9][10];
	static boolean[][] box = new boolean[9][10];
	
	static int[][] map = new int[9][9];
	static ArrayList<Zero> zero = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) zero.add(new Zero(i, j));
				else {
					//출현된 숫자 표시하기
					row[i][map[i][j]] = true;
					col[j][map[i][j]] = true;
					box[(i / 3) * 3 + (j / 3)][map[i][j]] = true;	//3x3 박스 인덱싱
				}
			}
		}
		
		combination(0);
		
		print();
	}
	
	//조합으로 가능한 숫자 넣어보기
	static boolean combination(int idx) {
		
		//basis part : 사전순으로 가장 작은 값을 출력하라 하였으므로 처음 발견하는 값을 출력하도록 dfs를 종료한다.
		if(idx == zero.size()) {
			return true;
		}
		
		Zero z = zero.get(idx);
		
		//1 ~ 9까지 채워보기
		for(int i = 1; i <= 9; i++) {
			//중복된 숫자가 없다면
			if(!row[z.r][i] && !col[z.c][i] && !box[(z.r / 3) * 3 + (z.c / 3)][i]) {
				map[z.r][z.c] = i;
				row[z.r][i] = true;
				col[z.c][i] = true;
				box[(z.r / 3) * 3 + (z.c / 3)][i] = true;
				
				if(combination(idx + 1)) return true;
				
				//map[z.r][z.c] = 0; 
				row[z.r][i] = false;
				col[z.c][i] = false;
				box[(z.r / 3) * 3 + (z.c / 3)][i] = false;
			}
		}
		return false;
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
