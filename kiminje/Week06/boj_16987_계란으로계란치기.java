package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ 16987. 계란으로 계란치기 */
public class Problem16987 {
    static int N, answer;
    static int[][] eggs;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input16987.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < 2; c++) {
                eggs[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 0);

        System.out.println(answer);

        br.close();
    }

    private static void go(int idx, int cnt) {
        answer = Math.max(answer, cnt);

        if (idx == N || answer == N) return;

        if (eggs[idx][0] < 1) {
            go(idx + 1, cnt);

            return;
        }

        boolean a = false;
        boolean b = false;

        for (int i = 0; i < N; i++) {
            if (eggs[idx][0] < 1) go(idx + 1, cnt);

            if (i == idx || eggs[i][0] < 1) continue;

            eggs[idx][0] -= eggs[i][1];
            eggs[i][0] -= eggs[idx][1];

            if (eggs[idx][0] < 1) {
                a = true;
                cnt++;
            }

            if (eggs[i][0] < 1) {
                b = true;
                cnt++;
            }

            go(idx + 1, cnt);

            if (a) {
                a = false;
                cnt--;
            }

            if (b) {
                b = false;
                cnt--;
            }

            eggs[idx][0] += eggs[i][1];
            eggs[i][0] += eggs[idx][1];
        }
    }
}
