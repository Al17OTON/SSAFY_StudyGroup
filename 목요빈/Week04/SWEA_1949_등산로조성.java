package 목요빈.Week04;

import java.util.*;
public class SWEA_1949_등산로조성{
	static int N, K, ans;
    static int[][] board;
    static boolean[][] visited;
    static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt(); // <= 8
            K = sc.nextInt();
 
            visited = new boolean[N][N];
            board = new int[N][N];
            int max = 0;
            ans = 0;
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = sc.nextInt();
                    max = Math.max(max, board[i][j]);
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == max) {
                        recursive(i, j, max, 1, false);
                    }
                }
            }
 
            System.out.printf("#%d %d\n", t, ans);
        }
        sc.close();
    }
     
    static void recursive(int r, int c, int h, int cnt, boolean cut) {
        visited[r][c] = true;
         
        if(cnt > ans) ans = cnt;
         
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                // 현재 높이와 비교
                if (h > board[nr][nc]) {
                    recursive(nr, nc, board[nr][nc], cnt + 1, cut);
                }else {
                    if(cut) continue;
                    if (board[nr][nc] - K >= h) continue;
                     
                    if(board[nr][nc] == h) {
                        recursive(nr, nc, h-1, cnt+1, true);
                    }else {
                        int dif = Math.min(board[nr][nc]-h+1, K);
                        recursive(nr, nc, board[nr][nc]-dif, cnt+1, true);
                    }
                }
            }
        }
        visited[r][c] = false;
    }
}