package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* SWEA 5644. [모의 SW 역량테스트] 무선 충전 */
public class Problem5644 {
    static int[][] AP;
    static int[][][] map;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input5644.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[][] dir = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int[][] user = new int[2][M + 1];
            AP = new int[A][4];
            map = new int[11][11][8];

            int answer = 0;

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 1; j < M + 1; j++) {
                    user[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 4; j++) {
                    AP[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            createMap();
            int[] a = {1, 1};
            int[] b = {10, 10};

            for (int t = 0; t < M + 1; t++) {
                a[0] += dir[user[0][t]][0];
                a[1] += dir[user[0][t]][1];
                b[0] += dir[user[1][t]][0];
                b[1] += dir[user[1][t]][1];

                PriorityQueue<int[]> aa = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
                PriorityQueue<int[]> bb = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

                for (int i = 0; i < 8; i++) {
                    if (map[a[0]][a[1]][i] > 0) aa.offer(new int[] {i, map[a[0]][a[1]][i]});
                    if (map[b[0]][b[1]][i] > 0) bb.offer(new int[] {i, map[b[0]][b[1]][i]});
                }

                int[] at = {-1, 0};
                int[] bt = {-2, 0};

                if (!aa.isEmpty()) at = aa.poll();
                if (!bb.isEmpty()) bt = bb.poll();

                if (at[0] == bt[0]) {
                    if (aa.isEmpty() && bb.isEmpty()) {
                        answer += at[1];
                    } else if (!aa.isEmpty() && bb.isEmpty()) {
                        at = aa.poll();
                        answer += at[1] + bt[1];
                    } else if (aa.isEmpty() && !bb.isEmpty()) {
                        bt = bb.poll();
                        answer += at[1] + bt[1];
                    } else {
                        if (aa.peek()[1] > bb.peek()[1]) {
                            at = aa.poll();
                            answer += at[1] + bt[1];
                        } else {
                            bt = bb.poll();
                            answer += at[1] + bt[1];
                        }
                    }
                } else {
                    answer += at[1] + bt[1];
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        br.close();
    }

    private static void createMap() {
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        for (int i = 0; i < AP.length; i++) {
            int[] bc = AP[i];
            boolean[][] visited = new boolean[11][11];
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[] {bc[1], bc[0], 0});
            map[bc[1]][bc[0]][i] = bc[3];
            visited[bc[1]][bc[0]] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int[] d : dir) {
                    int nr = cur[0] + d[0];
                    int nc = cur[1] + d[1];

                    if (nr > 0 && nr < 11 && nc > 0 && nc < 11 && !visited[nr][nc] && cur[2] < bc[2]) {
                        visited[nr][nc] = true;
                        map[nr][nc][i] = bc[3];
                        queue.offer(new int[] {nr, nc, cur[2] + 1});
                    }
                }
            }
        }
    }
}
