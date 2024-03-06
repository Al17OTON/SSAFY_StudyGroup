package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* SWEA 4013. [모의 SW 역량테스트] 특이한 자석 */
public class Problem4013 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input4013.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            int K = Integer.parseInt(br.readLine());

            int[][] mag = new int[4][8];

            for (int r = 0; r < 4; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < 8; c++) {
                    mag[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int[] first = new int[4];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                int idx = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());
                int right = mag[idx][(first[idx] + 2) % 8];
                int left = mag[idx][(first[idx] + 6) % 8];

                first[idx] = (first[idx] - dir + 8) % 8;

                int nextDir = dir;

                for (int i = idx + 1; i < 4; i++) {
                    if (right == mag[i][(first[i] + 6) % 8]) {
                        break;
                    }

                    right = mag[i][(first[i] + 2) % 8];

                    nextDir *= -1;

                    first[i] = (first[i] - nextDir + 8) % 8;
                }

                nextDir = dir;

                for (int i = idx - 1; i >= 0; i--) {
                    if (left == mag[i][(first[i] + 2) % 8]) {
                        break;
                    }

                    left = mag[i][(first[i] + 6) % 8];

                    nextDir *= -1;

                    first[i] = (first[i] - nextDir + 8) % 8;
                }
            }

            int answer = 0;

            for (int i = 0; i < 4; i++) {
                if (mag[i][first[i]] == 1) {
                    answer += Math.pow(2, i);
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        br.close();
    }
}
