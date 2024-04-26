package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* BOJ 17472. 다리 만들기 2 */
public class Problem17472 {
    static int N, M, cntIsland, answer;
    static int parent[];
    static int[][] map, dic = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static PriorityQueue<Bridge> bridges = new PriorityQueue<>((o1, o2) -> o1.l - o2.l);

    static class Bridge {
        int a;
        int b;
        int l;

        public Bridge(int a, int b, int l) {
            this.a = a;
            this.b = b;
            this.l = l;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input17472.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        getIsland();
        getBridge();

        System.out.println(calcBridge() ? answer : -1);

        br.close();
    }

    private static void getIsland() {
        int num = 1;

        boolean[][] visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (visited[r][c]) {
                    continue;
                }

                if (map[r][c] != 1) {
                    continue;
                }

                ArrayDeque<int[]> queue = new ArrayDeque<>();

                visited[r][c] = true;
                map[r][c] = num;
                queue.offer(new int[] {r, c});

                while (!queue.isEmpty()) {
                    int[] p = queue.poll();

                    for (int[] d : dic) {
                        int nr = p[0] + d[0];
                        int nc = p[1] + d[1];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            continue;
                        }

                        if (visited[nr][nc]) {
                            continue;
                        }

                        if (map[nr][nc] != 1) {
                            continue;
                        }

                        visited[nr][nc] = true;
                        map[nr][nc] = num;

                        queue.offer(new int[] {nr, nc});
                    }
                }

                num++;
            }
        }

        parent = new int[num];

        for (int i = 1; i < num; i++) {
            parent[i] = i;
        }

        cntIsland = num - 1;
    }

    private static void getBridge() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) {
                    continue;
                }

                for (int[] d : dic) {
                    int cnt = 1;

                    while (true) {
                        int nr = r + d[0] * cnt;
                        int nc = c + d[1] * cnt;

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            break;
                        }

                        if (map[nr][nc] == 0) {
                            cnt++;
                        } else if (map[nr][nc] == map[r][c]) {
                            break;
                        } else {
                            if (cnt < 3) {
                                break;
                            }

                            bridges.offer(new Bridge(map[r][c], map[nr][nc], cnt - 1));

                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean calcBridge() {
        int cnt = 0;

        while (!bridges.isEmpty()) {
            if (cnt == cntIsland) {
                break;
            }

            Bridge bridge = bridges.poll();

            if (findSet(bridge.a) == findSet(bridge.b)) {
                continue;
            }

            unionSet(bridge.a, bridge.b);

            answer += bridge.l;
            cnt++;
        }

        if (cnt != cntIsland - 1) {
            return false;
        }

        return true;
    }

    private static void unionSet(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    private static int findSet(int n) {
        if (parent[n] == n) {
            return n;
        } else {
            return parent[n] = findSet(parent[n]);
        }
    }
}
