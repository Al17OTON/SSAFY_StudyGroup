package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ 14502. 연구소 */
public class Problem14502 {
    static int N, M, answer;
    static int[][] map, dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input14502.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < map[r].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        buildWall(0);

        System.out.println(answer);

        br.close();
    }

    private static void buildWall(int cnt) {
        if (cnt == 3) {
            spreadVirus();

            return;
        }

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 0) {
                    map[r][c] = 1;
                    buildWall(cnt + 1);
                    map[r][c] = 0;
                }
            }
        }
    }

    private static void spreadVirus() {
        int nr, nc;
        int[] cur;
        int[][] tempMap = copyMap();
        Queue<int[]> queue = new ArrayDeque<>();

        for (int r = 0; r < tempMap.length; r++) {
            for (int c = 0; c < tempMap[r].length; c++) {
                if (tempMap[r][c] == 2) {
                    queue.offer(new int[] {r, c});

                    while (!queue.isEmpty()) {
                        cur = queue.poll();

                        for (int d = 0; d < dir.length; d++) {
                            nr = cur[0] + dir[d][0];
                            nc = cur[1] + dir[d][1];

                            if (nr >= 0 && nr < N && nc >= 0 && nc < M
                                    && tempMap[nr][nc] == 0) {
                                tempMap[nr][nc] = 2;
                                queue.offer(new int[] {nr, nc});
                            }
                        }
                    }
                }
            }
        }

        answer = Math.max(answer, getSafeZone(tempMap));
    }

    private static int getSafeZone(int[][] arr) {
        int result = 0;

        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                if (arr[r][c] == 0) result++;
            }
        }

        return result;
    }

    private static int[][] copyMap() {
        int[][] result = new int[N][M];

        for (int i = 0; i < result.length; i++) {
            result[i] = map[i].clone();
        }

        return result;
    }
}
