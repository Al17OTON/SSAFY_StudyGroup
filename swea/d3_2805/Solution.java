package swea.d3_2805;

import java.util.Scanner;

// swea d3 2805 : 농작물 수확하기
public class Solution {
	public static void main(String args[]) throws Exception
    {
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[][] board = new int[N][N];
            int ans = 0;
             
            for(int i = 0; i<N; i++) {
                String str = sc.next();
                for(int j = 0 ; j<N; j++) {
                    board[i][j] = str.charAt(j)-'0';
                }
            }
             
            for(int i = 1; i<=N; i++) {
                int idx = i;
                if(i > N/2 + 1)
                    idx = N + 1 - i;
                int n = 2 * idx - 1; // 수확하는 칸 개수
                int pass = (N-n)/2; // 지나가는 칸
 
                for(int j = pass; j < pass + n; j++) {
                    ans += board[i-1][j];
                }
            }
             
            System.out.println("#" + test_case + " " + ans);
        }
        sc.close();
    }
}
