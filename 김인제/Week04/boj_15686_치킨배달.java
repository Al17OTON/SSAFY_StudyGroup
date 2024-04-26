package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* BOJ 15686. 치킨 배달 */
public class Problem15686 {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;
    static List<int[]> chicken = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input15686.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < map[r].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 2) chicken.add(new int[] {r, c});
            }
        }

        pickChicken(0, 0, new int[M]);

        System.out.println(answer);

        br.close();
    }

    private static void pickChicken(int depth, int idx, int[] picks) {
        if (idx == M) {
            getMinChickenLoad(picks);

            return;
        }

        if (depth == chicken.size()) return;

        picks[idx] = depth;

        pickChicken(depth + 1, idx + 1, picks);
        pickChicken(depth + 1, idx, picks);
    }

    private static void getMinChickenLoad(int[] picks) {
        int total = 0, temp;

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 1) {
                    temp = Integer.MAX_VALUE;

                    for (int pick : picks) {
                        temp = Math.min(temp, (Math.abs(r - chicken.get(pick)[0]) + Math.abs(c - chicken.get(pick)[1])));
                    }

                    total += temp;

                    if (total > answer) return;
                }
            }
        }

        answer = Math.min(answer, total);
    }
}
