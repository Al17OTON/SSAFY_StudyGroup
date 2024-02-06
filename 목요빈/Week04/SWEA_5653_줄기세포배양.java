package 목요빈.Week04;

import java.util.*;

class Cell{
	int r, c, life,  live, time;
	public Cell(int r, int c, int live, int life, int time) {
		this.r = r;
		this.c = c;
		this.live = live; // 상태 -> 활성(1), 비활성(0)
		this.life = life; // 생명력 수치
		this.time = time; // 현재 시간
	}
	@Override
	public String toString() {
		return "Cell [r=" + r + ", c=" + c + ", life=" + life + ", live=" + live + ", time=" + time + "]";
	}

}

public class SWEA_5653_줄기세포배양 {
	
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	static int[][][] board;
	static int ans, K;
	static Queue<Cell> que;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int t = 1; t <= T; t++){
        	int N = sc.nextInt(); // 세로 크기
        	int M = sc.nextInt(); // 가로 크기
        	K = sc.nextInt(); // 총 배양 시간 -> K시간 후 살아있는 줄기세포의 총 개수
        	ans = 0;
        	
        	que = new LinkedList<>();
        	board = new int[K*2 + N][K*2 + M][2];
        	// board[i][j][0] : life, board[i][j][1] : time
        	for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					board[K+r][K+c][0] = sc.nextInt();
					if(board[K+r][K+c][0] == 0) continue;
					que.add(new Cell(K+r, K+c, 0, board[K+r][K+c][0], 0));
				}
			}
        	
        	for (int i = 1; i <= K; i++) { // 1초마다 bfs 돌리기
        		// Cell의 time과 전체 time은 다름
				bfs(i);
			}
        	
        	while(!que.isEmpty()) {
        		Cell temp = que.poll();
        		if(temp.life == board[temp.r][temp.c][0]) ans += 1;
        	}
        	
        	System.out.printf("#%d %d\n", t, ans);
    	}		
        sc.close();
	}

	
//	int r, int c, int live, int life, int time
//	live: 현재 상태(0, 1), life: 생명력, time: 몇 시간 지났는지
	static void bfs(int now) {
		int size = que.size();
		for(int i = 0; i<size; i++) {
			Cell temp = que.poll();
			
			// 내가 있을 자리에 다른 세포가 있으면 넘어가기
			if(temp.life != board[temp.r][temp.c][0]) continue;
			
			// 활성 && 시간 1시간 => 새로운 셀 전파
			if(temp.live == 1 && temp.time == 0) {
				for (int j = 0; j < 4; j++) {
					int nr = temp.r + dr[j];
					int nc = temp.c + dc[j];
					
					// 다음 위치에 셀이 있는지 확인
					if(board[nr][nc][0] != 0) {
						if(board[nr][nc][1] < now) continue; // 현재보다 전에 놓아져 있었다면
						else if(board[nr][nc][1] == now) {
							if(board[nr][nc][0] < temp.life) {
								board[nr][nc][0] = temp.life;
								que.add(new Cell(nr, nc, 0, temp.life, 0));
							}
						}
					}else {
						board[nr][nc][0] = temp.life;
						board[nr][nc][1] = now;
						que.add(new Cell(nr, nc, 0, temp.life, 0));
					}
				}
			}
			
			// 다음에 상태가 바뀌는지
			if(temp.life == temp.time+1) {
				if(temp.live == 1) continue; // 활성 -> 죽음
				else if(temp.live == 0) { // 비활성 -> 활성
					que.add(new Cell(temp.r, temp.c, 1, temp.life, 0));
				}
			}else {
				que.add(new Cell(temp.r, temp.c, temp.live, temp.life, temp.time+1));
			}
		}
	}
}