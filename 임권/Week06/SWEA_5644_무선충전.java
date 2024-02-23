package Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {
	
	static class BC {
		int r, c, range, power;
		
		public BC(int r, int c, int range, int power) {
			super();
			this.r = r;
			this.c = c;
			this.range = range;
			this.power = power;
		}	
	}
	
	static class Player {
		int r, c;

		public Player(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	
	static int M, An, MAP_SIZE = 10, total;
	static boolean[][][] map;
	static int[] moveA, moveB;
	static BC[] bc;
	static Player A, B;
	
	static int[] dr = {0, -1, 0, 1, 0};	//이동X, 상 우 하 좌
	static int[] dc = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			An = Integer.parseInt(st.nextToken());
			
			total = 0;
			map = new boolean[MAP_SIZE][MAP_SIZE][An];
			moveA = new int[M];
			moveB = new int[M];
			bc = new BC[An + 1];
			A = new Player(0, 0);
			B = new Player(9, 9);
			
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				moveA[m] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				moveB[m] = Integer.parseInt(st.nextToken());
			}
			
			for(int a = 0; a < An; a++) {
				st = new StringTokenizer(br.readLine());
				bc[a] = new BC(Integer.parseInt(st.nextToken()) -1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				
				setRange(a);
				//printmap(a);
			}
			//파워 연산의 편의를 위해 마지막에 0파워 비컨추가
			bc[An] = new BC(0, 0, 0, 0);
			
			for(int time = 0; time <= M; time++) {
				List<Integer> alist = new ArrayList<>();
				List<Integer> blist = new ArrayList<>();
				
				for(int i = 0; i < An; i++) {
					if(map[A.r][A.c][i]) {
						alist.add(i);
					}
					if(map[B.r][B.c][i]) {
						blist.add(i);
					}
				}
				
				int maxPower = 0;
				//아래 반복문을 위해서 비어있다면 0파워 비컨 이라도 추가해주기
				if(alist.isEmpty()) alist.add(An);
				if(blist.isEmpty()) blist.add(An);
				
				//현재 연결된 비컨들 중 최대 충전 조합을 구하기
				for(int a = 0; a < alist.size(); a++) {
					int ai = alist.get(a);
					for(int b = 0; b < blist.size(); b++) {
						int bi = blist.get(b);
						
						if(ai == bi) {
							maxPower = Math.max(bc[ai].power, maxPower);
						} else {
							maxPower = Math.max(bc[ai].power + bc[bi].power, maxPower);
						}
						
					}
				}
				
				total += maxPower;
				
				if(time == M) break;
				
				A.r += dr[moveA[time]];
				A.c += dc[moveA[time]];
				B.r += dr[moveB[time]];
				B.c += dc[moveB[time]];
				
			}
			
			System.out.println("#" + t + " " + total);
		}
	}

	
	//dfs말고 bfs로 해야함
	static void setRange(int idx) {
		boolean[][] v = new boolean[MAP_SIZE][MAP_SIZE];
		Queue<Integer> q = new LinkedList<>();
		//좌표, 거리
		q.offer(bc[idx].c);
		q.offer(bc[idx].r);
		q.offer(0);
		
		v[bc[idx].c][bc[idx].r] = true;
		map[bc[idx].c][bc[idx].r][idx] = true;
		
		while(!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			int len = q.poll();
			
			if(len == bc[idx].range) continue;
			
			for(int i = 1; i < dr.length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr >= MAP_SIZE || nr < 0 || nc >= MAP_SIZE || nc < 0 || v[nr][nc]) continue;
				
				v[nr][nc] = true;
				map[nr][nc][idx] = true;
				q.offer(nr);
				q.offer(nc);
				q.offer(len + 1);
			}
		}
	}
	
	static void printmap(int idx) {
		System.out.println();
		
		for(int i = 0; i < MAP_SIZE; i++) {
			for(int j = 0; j < MAP_SIZE; j++) {
				if(map[i][j][idx]) System.out.print("1 ");
				else System.out.print("0 ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}























