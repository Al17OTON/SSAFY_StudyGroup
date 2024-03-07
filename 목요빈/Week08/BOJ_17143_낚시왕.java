package 목요빈.Week08;

import java.io.*;
import java.util.*;

public class BOJ_17143_낚시왕 {
  
  static int R, C, M;
  static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, 1, -1}; 
  static int[][] map;
  static Shark[] shark;
  static class Shark implements Comparable<Shark>{
     int r, c, s, d, size;

     public Shark(int r, int c, int s, int d, int size) {
        super();
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.size = size;
     }

     @Override
     public int compareTo(Shark o) {
    	 return this.r - o.r;
     }

	@Override
	public String toString() {
		return "S: " + size;
	}
  }
  
  public static void main(String[] args) throws NumberFormatException, IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringTokenizer st = new StringTokenizer(br.readLine());
     
     R = Integer.parseInt(st.nextToken());
     C = Integer.parseInt(st.nextToken()); // <= 100
     M = Integer.parseInt(st.nextToken()); // 상어의 수
     
     shark = new Shark[M+1]; /////////////////// 배열로 변경
     map = new int[R][C]; //////////// 상어의 위치에 idx 저장
     for (int i = 1; i <= M; i++) {
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken())-1;
        int size = Integer.parseInt(st.nextToken());
        
        shark[i] = new Shark(r, c, s, d, size);
        map[r][c] = i;
     }
     
     int sum = 0;
     for (int c = 0; c < C; c++) {
        int[][] sharkLoc = new int[R][C];
        // 상어 낚시 -> 땅과 제일 가까운 상어 잡기
        for (int r = 0; r < R; r++) { // max : 100
			if(map[r][c] != 0) {
				sum += shark[map[r][c]].size;
				shark[map[r][c]] = null; // 이 자리에 있던 상어 null 처리
				map[r][c] = 0; // 이 자리에 있던 상어를 잡음
				break;
			}
		}
        
        // 상어 이동 -> visited에 저장된 상어 객체를 인수로 넣어 삭제하기
        for (int i = 1; i <= M; i++) {
        	if(shark[i] == null) continue;
        	Shark s = shark[i];
        	int speed = s.s;
        	int dir = s.d;
        	int nr = s.r;
        	int nc = s.c;
        	while(speed >= 0) {
            	if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
            		// 방향 바꾸기
            		nr -= dr[dir];
            		nc -= dc[dir];
            		
            		if(dir == 0) dir = 1;
            		else if(dir == 1) dir = 0;
            		else if(dir == 2) dir = 3;
            		else dir = 2;
            		speed += 1;
            	}
            	
            	nr += dr[dir];
        		nc += dc[dir];
        		speed -= 1;
        	}
        	
        	nr -= dr[dir];
    		nc -= dc[dir];
    		s.d = dir; // 바뀐 방향 넣어주기
    		s.r = nr;
    		s.c = nc;
        	if(sharkLoc[nr][nc] == 0) sharkLoc[nr][nc] = i;
        	else { // 이미 해당 칸에 상어가 있으면
        		if(shark[sharkLoc[nr][nc]].size > s.size) shark[i] = null;
        		else {
        			shark[sharkLoc[nr][nc]] = null;
        			sharkLoc[nr][nc] = i;
        		}
        	}
    	}
        
        for (int i = 0; i < R; i++) {
			map[i] = sharkLoc[i].clone();
		}
     }
     System.out.println(sum);
     
  }
}