package Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Fish {
	int r, c, size, count;

	public Fish(int r, int c, int size, int count) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
		this.count = count;
	}
	
	public void eat(int r, int c) {
		this.r = r;
		this.c = c;
		count++;
		
		//자신의 크기만큼 먹었다면 성장하기
		if(count == size) {
			size++;
			count = 0;
		}
	}
}

class EatableFish {
	int r, c, len;

	public EatableFish(int r, int c, int len) {
		super();
		this.r = r;
		this.c = c;
		this.len = len;
	}
}

public class BOJ_16236_아기상어 {

	static Fish bs;	//babyShark
	static int N, minLen, fr, fc;
	static int[][] map;
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					map[r][c] = 0;
					bs = new Fish(r, c, 2, 0);
				}
			}
		}
		
		System.out.println(sim());
	}
	
	static int sim() {
		int time = 0;
		
		while(true) {
			fr = N;
			fc = N;
			minLen = Integer.MAX_VALUE;
			int t = bfs();
			//dfs(bs.r, bs.c, 0, new boolean[N][N]);
			
//			if(fr == N && fc == N) break;
//			
//			bs.eat(fr, fc);
//			map[fr][fc] = 0;
//			time += minLen;
			//print(time + " " + t + " size : " + bs.size);
			if(t == -1) break;
			time += t;
		}
		
		return time;
	}
	
	//bfs가 예제 4번을 통과를 못한다. 그래서 모든 경우를 다 봐서 조건에 맞는 물고기를 먹으러 가도록 하자.
	static void dfs(int r, int c, int len, boolean[][] v) {
		if(minLen < len) return;
		
		if(map[r][c] > 0 && map[r][c] < bs.size) {
			if(len < minLen) {
				minLen = len;
				fr = r;
				fc = c;
			} else if(len == minLen) {
				if(fr > r) {
					fr = r;
					fc = c;
				} else if(fc > c) {
					fr = r;
					fc = c;
				}
			}
			return;
		}
		
		for(int i = 0; i < dr.length; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];
			
			if(rr >= N || rr < 0 || cc >= N || cc < 0 || map[rr][cc] > bs.size || v[rr][cc]) continue;
			
			v[rr][cc] = true;
			dfs(rr, cc, len + 1, v);
			v[rr][cc] = false;
		}
		
	}
	
	static int bfs() {
		List<EatableFish> fish = new LinkedList<>();
		fish.add(new EatableFish(0, 0, 123456));
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(bs.r);	//좌표
		q.offer(bs.c);
		q.offer(0);		//이동거리
		boolean[][] v = new boolean[N][N];
		
		v[bs.r][bs.c] = true; 
		
		while(!q.isEmpty()) {
			int r = q.poll();
			int c = q.poll();
			int len = q.poll();
			
			//현재 최소값보다 크다면 스킵
			if(fish.get(0).len < len) continue;
			
			//먹을 수 있는 물고기
			if(map[r][c] > 0 && map[r][c] < bs.size) {
				//가장 가까운 물고기만 남기기
				if(fish.get(0).len > len) {	
					fish = new LinkedList<>();
				} 
				fish.add(new EatableFish(r, c, len));
				continue;
			}
			
			for(int i = 0; i < dr.length; i++) {
				int rr = r + dr[i];
				int cc = c + dc[i];
				
				if(rr >= N || rr < 0 || cc >= N || cc < 0 || v[rr][cc] || map[rr][cc] > bs.size) continue; 
			
				v[rr][cc] = true;
				q.offer(rr);
				q.offer(cc);
				q.offer(len + 1);
			}
		}	
		//먹을 수 있는 물고기가 없다면
		if(fish.get(0).len == 123456) return -1;
		
		//가장 왼쪽 위 물고기 찾기
		int rr = N, cc = N; 
		for(int i = 0; i < fish.size(); i++) {
			EatableFish f = fish.get(i);
			
			if(rr > f.r) {
				rr = f.r;
				cc = f.c;
			} else if(rr == f.r && cc > f.c) {
				rr = f.r;
				cc = f.c;
			}
		}
		bs.eat(rr, cc);
		map[rr][cc] = 0;
		
		return fish.get(0).len;
	}
	
	//디버깅용
	static void print(String a) {
		System.out.println();
		System.out.println("###### " + a + " #######");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == bs.r && j == bs.c) System.out.print(9 + " ");
				else System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}


















