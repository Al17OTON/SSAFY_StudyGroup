package Week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17143_낚시왕 {
	
	static class Shark {
		int r, c, s, d, z;
		boolean isdead;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + ", isdead=" + isdead + "]";
		}
	}
	
	static int R, C, M;
	static Shark[] shark;
	static int[][] map;
	static Queue<Integer> q = new LinkedList<>();
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()) + 1;
		
		map = new int[R][C];
		shark = new Shark[M];
		
		for(int m = 1; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;	//0, 0 좌표계로 변환
			int c = Integer.parseInt(st.nextToken()) - 1;	
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;	//0 ~ 3 범위로 변환
			int z = Integer.parseInt(st.nextToken());
			
			q.offer(m);
			shark[m] = new Shark(r, c, s, d, z);
			map[r][c] = m;
			
		}
		
		//print();
		
		int result = 0;
		for(int pos = 0; pos < C; pos++) {
			
			// 사냥하기
			for(int depth = 0; depth < R; depth++) {
				if(map[depth][pos] != 0) {
					result += shark[map[depth][pos]].z;
					shark[map[depth][pos]].isdead = true;
					//System.out.println(shark[map[depth][pos]]);
					map[depth][pos] = 0;
					break;
				}
				//if(depth == R - 1) System.out.println("NOPE");
			}
			
			//상어 이동하기
			int[][] next = new int[R][C];
			Queue<Integer> tmp = new LinkedList<>();
			while(!q.isEmpty()) {
				int n = q.poll();
				
				if(shark[n].isdead) continue;
				
				int rc = getWay(n);
				
				//상어의 방향에 따른 R C 축 좌표
				int nr = shark[n].r, nc = shark[n].c;
				if(shark[n].d == 0 || shark[n].d == 1) {
					nr = rc;
				} else if(shark[n].d == 2 || shark[n].d == 3) {
					nc = rc;
				}
				
				//도착한 위치의 상어가 더 큰 경우
				if(next[nr][nc] != 0 && shark[next[nr][nc]].z > shark[n].z) {
					shark[n].isdead = true;
					continue;
				//도착한 위치의 상어가 더 작은 경우 사냥하기
				} else if(next[nr][nc] != 0) {
					shark[next[nr][nc]].isdead = true;
				}
				shark[n].r = nr;
				shark[n].c = nc;
				next[nr][nc] = n;
				tmp.offer(n);
			}
			
			q = tmp;
			map = next;
			//print();
		}
		
		System.out.println(result);
	}
	
	//해당 상어의 방향에 따른 도착 좌표 계산
	static int getWay(int n) {
		Shark s = shark[n];
		if(s.d == 0) {
			int left = s.s - s.r;
			if(left <= 0) {
				return s.r - s.s;
			} else {
				int div = left / (R - 1);
				int mod = left % (R - 1);
				if(div % 2 == 0) {
					s.d = 1;
					return mod;
				}
				return R - 1 - mod;
			}
			
		} else if(s.d == 1) {
			int left = s.s - (R - 1 - s.r);
			if(left <= 0) {
				return s.r + s.s;
			} else {
				int div = left / (R - 1);
				int mod = left % (R - 1);
				if(div % 2 == 0) {
					s.d = 0;
					return R - 1 - mod;
				}
				return mod;
			}
		} else if(s.d == 2) {
			int left = s.s - (C - 1 - s.c);
			if(left <= 0) {
				return s.c + s.s;
			} else {
				int div = left / (C - 1);
				int mod = left % (C - 1);
				if(div % 2 == 0) {
					s.d = 3;
					return C - 1 - mod;
				}
				return mod;
			}
		} else {
			int left = s.s - s.c;
			if(left <= 0) {
				return s.c - s.s;
			} else {
				int div = left / (C - 1);
				int mod = left % (C - 1);
				if(div % 2 == 0) {
					s.d = 2;
					return mod;
				}
				return C - 1 - mod;
			}
		}
	}
	
	static void print() {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				Shark s = shark[map[r][c]];
				if(s == null) System.out.print("0 ");
				else System.out.print(s.z + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

















