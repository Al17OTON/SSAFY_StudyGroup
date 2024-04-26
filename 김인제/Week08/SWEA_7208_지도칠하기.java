package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* SWEA 7208. 지도 칠하기 */
public class Problem7208 {
    static int N, answer;
    static int[] color, per;
    static int[][] adjArr;

    static void getPer(int depth) {
        if (depth == N) {
            if (check()) {
                getMin();
            }

            return;
        }

        for (int i = 1; i < 5; i++) {
            per[depth] = i;
            getPer(depth + 1);
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (adjArr[i][j] == 0) {
                    continue;
                }

                if (per[i] == per[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void getMin() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (color[i] != per[i]) {
                cnt++;
            }
        }

        answer = Math.min(answer, cnt);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input7208.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            answer = Integer.MAX_VALUE;

            color = new int[N];
            per = new int[N];
            adjArr = new int[N][N];

            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                color[c] = Integer.parseInt(st.nextToken());
            }

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    adjArr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            getPer(0);

            System.out.println("#" + tc + " " + answer);
        }

        br.close();
    }
}
