package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/* BOJ 17281. 야구 */
public class Problem17281 {
    static int N, answer;
    static int[][] games;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input17281.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        games = new int[N][9];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < 9; c++) {
                games[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        pick(1, new int[9], new boolean[9]);

        System.out.println(answer);

        br.close();
    }

    private static void pick(int depth, int[] per, boolean[] visited) {
        if (depth == per.length) {
            int temp = per[0];
            per[0] = per[3];
            per[3] = temp;

            answer = Math.max(answer, getScore(per));

            temp = per[0];
            per[0] = per[3];
            per[3] = temp;

            return;
        }

        for (int i = 1; i < per.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                per[depth] = i;
                pick(depth + 1, per, visited);
                visited[i] = false;
            }
        }
    }

    private static int getScore(int[] per) {
        int result = 0;
        int idx = -1;

        for (int i = 0; i < N; i++) {
            int out = 0;
            ArrayDeque<Integer> base = new ArrayDeque<>();

            while (out < 3) {
                idx = (idx + 1) % 9;

                if (games[i][per[idx]] == 0) {
                    out++;
                } else if (games[i][per[idx]] == 4) {
                    result += base.size() + 1;
                    base.clear();
                } else {
                    int cnt = base.size();

                    for (int j = 0; j < cnt; j++) {
                        int next = base.poll() + games[i][per[idx]];

                        if (next >= 4) {
                            result++;
                        } else {
                            base.offer(next);
                        }
                    }

                    base.offer(games[i][per[idx]]);
                }
            }
        }

        return result;
    }
}
