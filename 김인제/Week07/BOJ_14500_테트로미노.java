package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ 14500. 테트로미노 */
public class Problem14500 {
    static int N, M;
    static int[][] paper;
    static int[][][] tetrominos = {
            {{0, 3}, {0, 2}, {0, 1}, {0, 0}},
            {{3, 0}, {2, 0}, {1, 0}, {0, 0}},

            {{1, 1}, {1, 0}, {0, 1}, {0, 0}},

            {{2, 1}, {2, 0}, {1, 0}, {0, 0}},
            {{2, -1}, {2, 0}, {1, 0}, {0, 0}},
            {{-1, 2}, {0, 2}, {0, 1}, {0, 0}},
            {{1, 2}, {0, 2}, {0, 1}, {0, 0}},
            {{-2, -1}, {-2, 0}, {-1, 0}, {0, 0}},
            {{-2, 1}, {-2, 0}, {-1, 0}, {0, 0}},
            {{1, -2}, {0, -2}, {0, -1}, {0, 0}},
            {{-1, -2}, {0, -2}, {0, -1}, {0, 0}},

            {{2, 1}, {1, 1}, {1, 0}, {0, 0}},
            {{2, -1}, {1, -1}, {1, 0}, {0, 0}},
            {{-1, 2}, {-1, 1}, {0, 1}, {0, 0}},
            {{1, 2}, {1, 1}, {0, 1}, {0, 0}},

            {{1, 1}, {0, 2}, {0, 1}, {0, 0}},
            {{-1, 1}, {0, 2}, {0, 1}, {0, 0}},
            {{1, -1}, {2, 0}, {1, 0}, {0, 0}},
            {{1, 1}, {2, 0}, {1, 0}, {0, 0}}
    };

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input14500.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                paper[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                L : for (int[][] tetromino : tetrominos) {
                    int sum = 0;

                    for (int[] dir : tetromino) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];

                        if (!check(nr, nc)) {
                            continue L;
                        }

                        sum += paper[nr][nc];
                    }

                    answer = Math.max(answer, sum);
                }
            }
        }

        System.out.println(answer);

        br.close();
    }

    private static boolean check(int r, int c) {
        if (r >= 0 && r < N && c >= 0 && c < M) {
            return true;
        }

        return false;
    }
}
