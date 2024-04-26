package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/* BOJ 17822. 원판 돌리기 */
public class Problem17822 {
    static int N, M, T;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input17822.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            cycle(x, d, k);
            delete();
        }

        System.out.println(cntNum());

        br.close();
    }

    private static void cycle(int X, int D, int K) {
        for (int x = X; x < N + 1; x *= 2) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int i : board[x - 1]) {
                queue.offer(i);
            }

            if (D == 0) {
                for (int i = 0; i < K; i++) {
                    queue.offerFirst(queue.pollLast());
                }
            } else {
                for (int i = 0; i < K; i++) {
                    queue.offerLast(queue.pollFirst());
                }
            }

            for (int i = 0; i < M; i++) {
                board[x - 1][i] = queue.pollFirst();
            }
        }
    }

    private static void delete() {
        boolean result = false;
        int[][] dic = {{0, 1}, {1, 0}, {0, -1}};
        boolean[][] checked = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 0) {
                    continue;
                }

                boolean flag = false;

                for (int[] d : dic) {
                    int nr = r + d[0];
                    int nc = (c + d[1] + M) % M;

                    if (nr >= N) {
                        continue;
                    }

                    if (board[r][c] == board[nr][nc]) {
                        checked[nr][nc] = true;
                        flag = true;
                        result = true;
                    }
                }

                if (flag) {
                    checked[r][c] = true;
                }
            }
        }

        if (result) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (checked[r][c]) {
                        board[r][c] = 0;
                    }
                }
            }
        } else {
            updown();
        }
    }

    private static void updown() {
        int sum = 0;
        int cnt = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 0) {
                    continue;
                }

                sum += board[r][c];
                cnt++;
            }
        }

        double avg = (double) sum / cnt;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 0) {
                    continue;
                }

                if (board[r][c] > avg) {
                    board[r][c]--;
                } else if (board[r][c] < avg) {
                    board[r][c]++;
                }
            }
        }
    }

    private static int cntNum() {
        int result = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                result += board[r][c];
            }
        }

        return result;
    }
}
