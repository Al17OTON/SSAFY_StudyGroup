package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ 17136. 색종이 붙이기 */
public class Problem17136 {
    static int answer = Integer.MAX_VALUE;
    static int[][] paper = new int[10][10];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input17136.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cover(0, new int[] {5, 5, 5, 5, 5}, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

        br.close();
    }

    private static void cover(int cCnt, int[] pCnt, int x, int y) {
        boolean start = true;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (start) {
                    start = false;
                    i = x;
                    j = y;
                }

                if (cCnt >= answer) {
                    return;
                }

                if (paper[i][j] == 1) {
                    for (int k = 4; k >= 0; k--) {
                        if (checkAble(i, j, k) && pCnt[k] > 0) {
                            insP(i, j, k);
                            pCnt[k]--;
                            cover(cCnt + 1, pCnt, i, j);
                            delP(i, j, k);
                            pCnt[k]++;
                        }
                    }

                    return;
                }
            }
        }

        answer = Math.min(answer, cCnt);
    }

    private static boolean checkAble(int x, int y, int n) {
        for (int i = x; i < x + n + 1; i++) {
            for (int j = y; j < y + n + 1; j++) {
                if (i >= 10 || j >= 10 || paper[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void insP (int x, int y, int n) {
        for (int i = x; i < x + n + 1; i++) {
            for (int j = y; j < y + n + 1; j++) {
                paper[i][j] = 0;
            }
        }
    }

    private static void delP (int x, int y, int n) {
        for (int i = x; i < x + n + 1; i++) {
            for (int j = y; j < y + n + 1; j++) {
                paper[i][j] = 1;
            }
        }
    }
}
