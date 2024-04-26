package 목요빈.Week05;

import java.io.*;
import java.util.*;

public class BOJ_16236_아기상어 {
	
	/*
	 나는 물고기를 리스트에 넣어서 아기 상어보다 작은 물고기들간 거리를 다 구함
	 * */
	static int[] dr = { -1, 0, 0, 1 }, dc = { 0, -1, 1, 0 };
	static int N, time, min_dis, eat, board[][];
	static Fish baby;
	static List<Fish> fish; 
	
	static class Fish implements Comparable<Fish>{
		int r, c, size;

		public Fish(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}
		
		public Fish(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Fish o) {
			// 크기 작은 순
			return this.size-o.size;
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		fish = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 9) {
					// 아기상어 위치 저장
					baby = new Fish(i, j, 2);
					board[i][j] = 0; ////////////// 이거!
				}else if(board[i][j] >= 1) {
					// 물고기 위치 저장
					fish.add(new Fish(i, j, board[i][j]));
				}
			}
		}
		
		// 사이즈, 위치 별 저장
		Collections.sort(fish);
		if(fish.size() == 0) System.out.println(0);
		else simul();
	}
	
	static void simul() {
		// 내가 현재 먹은 물고기 수 == 현재 크기이면, 현재 크기 + 1;
		while(fish.size() > 0) {
			// fish[0]을 못먹으면 엄마에게 도움 요청
			Fish cur = fish.get(0);
			if(cur.size >= baby.size) break;

			// 먹을 수 있는 물고기에 도달을 못하면 엄마 도와줘~
			min_dis = Integer.MAX_VALUE;
			Fish min_fish = null;
			for (int i = 0; i < fish.size(); i++) {
				Fish f = fish.get(i);
				if(f.size >= baby.size) break;
				// 먹을 수 있는 물고기 중 가장 가까운 물고기 구하기
				// dis == min 통해 크기가 다르지만 dis가 같은 경우 처리
				int dis = bfs(baby, f);
				if(dis == 0) continue;
				if(dis < min_dis) { ///////// 이거!
					min_dis = dis;
					min_fish = f;
				}if(dis == min_dis) {
					if(min_fish.r == f.r) {
						if(min_fish.c > f.c) {
							min_fish = f;
							min_dis = dis;
						}
					}else if(min_fish.r > f.r){
						min_fish = f;
						min_dis = dis;
					}
				}
			}
			if(min_dis == 0 || min_fish == null) break;
			
			// 물고기 위치로 이동 후 먹기
			time += min_dis;
			baby.r = min_fish.r;
			baby.c = min_fish.c;
			eat += 1;
			board[min_fish.r][min_fish.c] = 0; //////////이거!
			fish.remove(min_fish);
				
			if(eat == baby.size) {
				baby.size += 1;
				eat = 0; //////// 이거!
			}
		}
		System.out.println(time);
	}
	
	static int bfs(Fish baby, Fish dest) {
		Queue<Fish> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int count = 0;
		boolean flag = false;
		
		que.add(new Fish(baby.r, baby.c));
		visited[baby.r][baby.c] = true;
		
		L: while(!que.isEmpty()) {
			if(min_dis != Integer.MAX_VALUE && count > min_dis) break; // min보다 멀리 있으면 break;
			int size = que.size();
			for (int i = 0; i < size; i++) {
				Fish temp = que.poll();
				if(temp.r == dest.r && temp.c == dest.c) {
					flag = true;
					break L;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = temp.r + dr[d];
					int nc = temp.c + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					// 자신보다 큰 물고기가 있는 곳은 못지나감
					if(board[nr][nc] > baby.size) continue;
					if(visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					que.add(new Fish(nr, nc));
				}
			}
			count += 1;
		}
		
		// 아기상어 -> 물고기까지의 거리, 갈 수 없으면 0 return;
		return flag ? count : 0;
	}
}
