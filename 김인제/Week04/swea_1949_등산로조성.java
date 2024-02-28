package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* SWEA 1949. [모의 SW 역량테스트] 등산로 조성 */
public class Problem1949 {
    static int N, K, mh, answer;
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input1949.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            mh = 0;
            map = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    mh = Math.max(mh, map[i][j]);
                }
            }

            answer = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == mh) {
                        dfs(i, j, 1, true);
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }

        br.close();
    }

    private static void dfs(int x, int y, int cnt, boolean cutAble) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                if (map[x][y] > map[nx][ny]) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, cnt + 1, cutAble);
                    visited[nx][ny] = false;
                } else if (cutAble) {
                    for (int j = 1; j < K + 1; j++) {
                        if (map[x][y] > map[nx][ny] - j) {
                            visited[nx][ny] = true;
                            map[nx][ny] -= j;
                            dfs(nx, ny, cnt + 1, false);
                            visited[nx][ny] = false;
                            map[nx][ny] += j;
                        }
                    }
                }
            }
        }

        visited[x][y] = false;

        answer = Math.max(answer, cnt);
    }
}
