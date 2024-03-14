import java.io.*;
import java.util.*;

public class Main {

	static int R, C;
	static char[][] board;
	static Pos[] loc;
	static Queue<Pos> moveq, meltq;
	static boolean[][] visited;
	static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
	
	static class Pos{
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	/*
	 * 각 단계에서 큐를 활용해 위치 저장
	 1. 백조 이동
	 2. 얼음 녹이기
	 * */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];// <= 1500
		meltq = new LinkedList<>();
		moveq = new LinkedList<>();
		loc = new Pos[2];
		
		for (int i = 0, idx=0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j] == 'L') {
					loc[idx++] = new Pos(i, j);
					board[i][j] = '.';
				}
				if(board[i][j] == '.') meltq.add(new Pos(i, j));
			}
		}

		visited = new boolean[R][C];
		moveq.add(new Pos(loc[0].r, loc[0].c));
		visited[loc[0].r][loc[0].c] = true;
		
		int cnt = 0;
		while(true) {
			if(move()) break; // bfs 수정
			melt();
			cnt += 1;
		}
		
		System.out.println(cnt);
	}
	
	static boolean move() {
		Queue<Pos> temp = new LinkedList<>();
		
		while(!moveq.isEmpty()) {
			Pos t = moveq.poll();
			
			if(t.r == loc[1].r && t.c == loc[1].c) return true;
			for (int d = 0; d < 4; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
				
				visited[nr][nc]	= true;
				if(board[nr][nc] == 'X') {
					temp.add(new Pos(nr,nc)); // 다음에 녹을 빙하
				}else if(board[nr][nc] == '.') {
					moveq.offer(new Pos(nr, nc));
				}
			}
		}

		moveq = temp;
		return false;
	}
	
	static void melt() {
		int size = meltq.size();
		for (int i = 0; i < size; i++) {
			Pos t = meltq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C ) continue;
				
				if(board[nr][nc] == 'X') { // 옆칸이 물 = 물은 이미 집합에 포함되었음(아니면 어카징)
					board[nr][nc] = '.';
					meltq.add(new Pos(nr, nc));
				}
			}
		}
	}
}

