package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ 17070. 파이프 옮기기 1 */
public class Problem17070 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input17070.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][][] dp = new int[3][N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int c = 1; c < N; c++) {
            if (map[0][c] == 0) {
                dp[0][0][c]++;
            } else {
                break;
            }
        }

        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                if (map[r][c] == 1) {
                    continue;
                }

                dp[0][r][c] = dp[0][r][c - 1] + dp[1][r][c - 1];
                dp[2][r][c] = dp[1][r - 1][c] + dp[2][r - 1][c];

                if (map[r - 1][c] == 0 && map[r][c - 1] == 0) {
                    dp[1][r][c] = dp[0][r - 1][c - 1] + dp[1][r - 1][c - 1] + dp[2][r - 1][c - 1];
                }
            }
        }

        System.out.println(dp[0][N - 1][N - 1] + dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1]);

        br.close();
    }
}
