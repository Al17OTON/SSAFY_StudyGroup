package Week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3197_백조의호수 {
	
	static class RC {
		int r, c;
		public RC(int r, int c) {this.r = r; this.c = c;}
	}

	//2차원 유니온 파인드
	static class Union {
		
		RC[][] uni;
		
		public Union(int height, int weidth) {
			uni = new RC[height][weidth];
			
			for(int r = 0; r < height; r++) {
				for(int c = 0; c < weidth; c++) {
					uni[r][c] = new RC(r, c);
				}
			}
		}
		
		RC find(int r, int c) {
			RC tmp = uni[r][c];
			if(tmp.r == r && tmp.c == c) return tmp;
			return uni[r][c] = find(tmp.r, tmp.c);
		}
		
		boolean setUnion(int r, int c, int rr, int cc) {
			RC A = find(r, c);
			RC B = find(rr, cc);
			
			if(A == B) return false;
			
			uni[B.r][B.c]= A;
			return true;
		}
		
		boolean isUnion(int r, int c, int rr, int cc) {
			RC A = find(r, c);
			RC B = find(rr, cc);
			
			if(A == B) return true;
			
			return false;
		}
		
	}
	
	static int Ar = -1, Br, Ac, Bc;
	
	static int R, C;
	static char[][] map;
	static Union u;
	static Queue<RC> shore;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		u = new Union(R, C);
		shore = new LinkedList<>();
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 'L') {
					map[r][c] = '.';
					if(Ar == -1) {
						Ar = r;
						Ac = c;
					} else {
						Br = r;
						Bc = c;
					}
				}
			}
		}
		
		//빙하가 아닌 부분은 이웃한 물과 유니온파인드 연산을 수행하고
		//빙하인 부분은 물과 인접하다면 큐에 삽입
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == '.') {
					for(int i = 0; i < dr.length; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if(nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] == 'X') continue;
						u.setUnion(r, c, nr, nc);
					}	
				} else {
					for(int i = 0; i < dr.length; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						if(nr >= R || nr < 0 || nc >= C || nc < 0 || map[nr][nc] == 'X') continue;
						shore.offer(new RC(r, c));
						break;
					}	
				}
			}
		}
		
		//두 백조가 연결된 물에 위치하는지 확인하는 연산을 수행
		int time = 0;
		boolean[][] v = new boolean[R][C];
		while(!u.isUnion(Ar, Ac, Br, Bc)) {
			time++;
			
			Queue<RC> tmp = new LinkedList();
			
			//빙하를 녹인다.
			while(!shore.isEmpty()) {
				RC rc = shore.poll();
				
				map[rc.r][rc.c] = '.'; 
				
				//자신이 녹음으로 인해 물과 만나게된 인접한 빙하들을 큐에 삽입한다.
				for(int i = 0; i < dr.length; i++) {
					int nr = rc.r + dr[i];
					int nc = rc.c + dc[i];
					
					if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;
					if(map[nr][nc] == '.') {
						u.setUnion(nr, nc, rc.r, rc.c);
						continue;
					}
					
					if(!v[nr][nc]) {
						v[nr][nc] = true;
						tmp.offer(new RC(nr, nc));
					}
				}
				
			}
			
			shore = tmp;
			
		}
		
		System.out.println(time);
		
	}

}






















