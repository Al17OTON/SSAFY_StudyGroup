package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* BOJ 16236. 아기 상어 */
public class Problem16236 {
    static int N;
    static int[] sharkInfo;
    static int[][] map, dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input16236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int r = 0; r < map.length; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < map[r].length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if (map[r][c] == 9) {
                    sharkInfo = new int[] {r, c, 0, 2, 2};
                    map[r][c] = 0;
                }
            }
        }

        int[] target;

        while (true) {
            target = find(sharkInfo[0], sharkInfo[1], 0, sharkInfo[3]);

            if (target == null) break;

            map[target[0]][target[1]] = 0;
            sharkInfo[0] = target[0];
            sharkInfo[1] = target[1];
            sharkInfo[2] += target[2];
            sharkInfo[4]--;

            if (sharkInfo[4] == 0) {
                sharkInfo[3]++;
                sharkInfo[4] = sharkInfo[3];
            }
        }

        System.out.println(sharkInfo[2]);

        br.close();
    }

    private static int[] find(int r, int c, int time, int size) {
        int nr, nc;
        int[] cur;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        boolean stop = false;
        int stopTime = 0;

        queue.offer(new int[] {r, c, time});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            cur = queue.poll();

            if (stop && cur[2] == stopTime) break;

            for (int d = 0; d < dir.length; d++) {
                nr = cur[0] + dir[d][0];
                nc = cur[1] + dir[d][1];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    if (map[nr][nc] == 0 || map[nr][nc] == size) {
                        visited[nr][nc] = true;
                        queue.offer(new int[] {nr, nc, cur[2] + 1});
                    } else if (map[nr][nc] < size) {
                        pq.offer(new int[] {nr, nc, cur[2] + 1});
                        stop = true;
                        stopTime = cur[2] + 1;
                    }
                }
            }
        }

        return pq.poll();
    }
}
