package com.algorithm.solution.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* SWEA 1767. [SW Test 샘플문제] 프로세서 연결하기 */
public class Problem1767 {
    static int N;
    static int[] answer;
    static int[][] processer, dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[] visited;
    static List<int[]> core = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input1767.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T + 1; tc++) {
            N = Integer.parseInt(br.readLine());
            processer = new int[N][N];
            core.clear();

            for (int r = 0; r < processer.length; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < processer[r].length; c++) {
                    processer[r][c] = Integer.parseInt(st.nextToken());
                    if (processer[r][c] == 1) core.add(new int[] {r, c});
                }
            }

            answer = new int[2];
            visited = new boolean[core.size()];

            connect(0, 0);

            System.out.println("#" + tc + " " + answer[0]);
        }

        br.close();
    }

    private static void connect(int lineCnt, int coreCnt) {
        if (answer[1] < coreCnt) {
            answer[0] = lineCnt;
            answer[1] = coreCnt;
        } else if (answer[1] == coreCnt) {
            answer[0] = Math.min(answer[0], lineCnt);
        }

        for (int i = 0; i < core.size(); i++) {
            if (answer[1] > coreCnt + (core.size() - i)) return;

            if (!visited[i]) {
                visited[i] = true;

                int n, nr, nc;
                int[] cur = core.get(i);

                for (int d = 0; d < dir.length; d++) {
                    n = 0;

                    while (true) {
                        n++;
                        nr = cur[0] + (dir[d][0] * n);
                        nc = cur[1] + (dir[d][1] * n);

                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            connect(lineCnt, coreCnt + 1);

                            break;
                        }

                        if (processer[nr][nc] > 0) break;

                        processer[nr][nc] = 2;
                        lineCnt++;
                    }

                    while (true) {
                        n--;
                        nr = cur[0] + (dir[d][0] * n);
                        nc = cur[1] + (dir[d][1] * n);

                        if (nr == cur[0] && nc == cur[1]) {
                            break;
                        }

                        processer[nr][nc] = 0;
                        lineCnt--;
                    }
                }

                visited[i] = false;
            }
        }
    }
}
