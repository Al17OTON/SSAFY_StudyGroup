package com.kijen.algorithm.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/* BOJ 3197. 백조의 호수 */
public class Problem3197 {
    static int R, C, r1, c1, r2, c2;
    static int[][] map, dic = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[][] visited;
    static ArrayDeque<int[]> water, move;

    static boolean meet() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        while (!move.isEmpty()) {
            int[] cur = move.poll();

            if (cur[0] == r2 && cur[1] == c2) {
                return true;
            }

            for (int[] d : dic) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;

                if (map[nr][nc] == 0) {
                    move.offer(new int[] {nr, nc});
                } else {
                    queue.offer(new int[] {nr, nc});
                }
            }
        }

        move = queue;

        return false;
    }

    static void melt() {
        int size = water.size();

        for (int i = 0; i < size; i++) {
            int[] cur = water.poll();

            for (int[] d : dic) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    continue;
                }

                if (map[nr][nc] == 1) {
                    map[nr][nc] = 0;
                    water.offer(new int[] {nr, nc});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input3197.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];
        water = new ArrayDeque<>();
        move = new ArrayDeque<>();

        boolean check = false;

        for (int r = 0; r < R; r++) {
            String input = br.readLine();

            for (int c = 0; c < C; c++) {
                if (input.charAt(c) == 'X') {
                    map[r][c] = 1;
                } else if (input.charAt(c) == 'L') {
                    water.offer(new int[] {r, c});

                    if (!check) {
                        check = true;
                        r1 = r;
                        c1 = c;
                    } else {
                        r2 = r;
                        c2 = c;
                    }
                } else {
                    water.offer(new int[] {r, c});
                }
            }
        }

        visited[r1][c1] = true;
        move.offer(new int[] {r1, c1});

        int answer = 0;

        while (true) {
            if (meet()) {
                break;
            }

            melt();
            answer++;
        }

        System.out.println(answer);

        br.close();
    }
}
