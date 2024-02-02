package SWEA;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16234_인구이동 {
	
	static boolean flag = true;
	static Queue<Integer> q = new LinkedList<>();
	static int[][] map;
	static boolean[][] united;
	static int N, L, R, sum;
	static int[] dirX = {-1, 1, 0, 0};	//상하좌우
	static int[] dirY = {0, 0, -1, 1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		System.out.println(sim());
	}
	
	static int sim() {
		int result = 0;
		int ii, jj;
		while(flag) {
			flag = false;				//국가간 인구 이동이 있었는지 체크
			united = new boolean[N][N];	//매일 국경 초기화
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					sum = 0;
					if(!united[i][j]) {
						united[i][j] = true;
						q.add(i); q.add(j);
						sum += map[i][j];
						checkUnite(i, j);
						sum /= q.size() / 2;
						while(!q.isEmpty()) {
							ii = q.poll();
							jj = q.poll();
							
							map[ii][jj] = sum;
						}
					}
				}
			}
			//if(flag) result++;	//인구 이동이 있었다면 날짜 세기
			result++;
		}
		
		return result - 1;
	}
	
	// DFS로 연합이 가능한 모든 국가를 큐에 삽입한다.
	static void checkUnite(int i, int j) {
		int ii, jj;
		for(int a = 0; a < dirX.length; a++) {
			ii = i + dirX[a]; jj = j + dirY[a];
			if(ii >= N || ii < 0 || jj >= N || jj < 0 || united[ii][jj]) continue;
			int sub = Math.abs(map[ii][jj] - map[i][j]);
			if(sub >= L && sub <= R) {
				flag = true;			//한번이라도 인구 이동이 일어난다면 체크
				q.add(ii); q.add(jj);
				sum += map[ii][jj];
				united[ii][jj] = true;
				checkUnite(ii, jj);
			}
		}
		
	}
}
