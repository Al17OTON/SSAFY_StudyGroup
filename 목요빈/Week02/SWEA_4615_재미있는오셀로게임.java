package 목요빈.Week02;

import java.util.Scanner;

// SWEA D3 4615 : 재미있는오셀로게임
public class SWEA_4615_재미있는오셀로게임 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        // 시계방향
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
         
        for(int t = 1; t <= T; t++) {
            int N = sc.nextInt(); // 보드의 크기
            int M = sc.nextInt();
             
            int[][] board = new int[N][N];
            int r, c; 
            int b = 0, w = 0;
            // 1 : 흑, 2 : 백
            board[N/2-1][N/2-1] = 2;
            board[N/2][N/2] = 2;
            board[N/2-1][N/2] = 1;
            board[N/2][N/2-1] = 1;
             
            for (int i = 0; i < M; i++) {
                r = sc.nextInt()-1;
                c = sc.nextInt()-1;
                board[r][c] = sc.nextInt();
                int color = board[r][c] == 1 ? 2 : 1; // 현재 놓은 색과 다른 색
                 
                for (int j = 0; j < 8; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                     
                    boolean flag = false;
                    while(nr >= 0 && nr < N & nc >= 0 && nc < N && board[nr][nc] != 0) {
                        if(board[nr][nc] != color) { // 다음 돌이 놓은 돌과 색이 같을 경우
                        	flag = true;
                            break;
                        }
                        nr += dr[j];
                        nc += dc[j];
                    }
                     
                    while(flag) {
                        if(nc == c && nr ==r) break;
                        board[nr][nc] = board[r][c];
                        nr -= dr[j];
                        nc -= dc[j];
                    }
                }
            }
             
            for (int[] arr : board) {
                for(int num : arr) {
                    if(num == 1) b += 1;
                    else if(num == 2) w += 1;
                }
            }
             
            System.out.println("#" + t + " " + b + " " + w);
        }
        sc.close();
    }
}
