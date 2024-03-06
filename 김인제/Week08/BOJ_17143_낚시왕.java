package com.algorithm.solution.boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* BOJ 17143. 낚시왕 */
public class Problem17143 {
    static int[][] dir = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
    static class Shark {
        int r, c, s, d, z;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/boj/Input17143.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[][] map = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) % 4;
            int z = Integer.parseInt(st.nextToken());

            map[r][c] = new Shark(r, c, s, d, z);
        }

        int answer = 0;
        
        for (int mc = 0; mc < C; mc++) {
            for (int mr = 0; mr < R; mr++) {
                if (map[mr][mc] != null) {
                    answer += map[mr][mc].z;
                    map[mr][mc] = null;

                    break;
                }
            }

            Shark[][] newMap = new Shark[R][C];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] == null) {
                        continue;
                    }

                    Shark shark = map[r][c];

                    int move = 0;

                    if (shark.d == 0 || shark.d == 3) {
                        int nc = shark.c;
                        int nd = shark.d;

                        move = shark.s % ((C - 1) * 2);

                        for (int i = 0; i < move; i++) {
                            if (nc == 0) {
                                nd = 3;
                            } else if (nc == C - 1) {
                                nd = 0;
                            }

                            nc += dir[nd][1];
                        }

                        shark.c = nc;
                        shark.d = nd;
                    } else {
                        int nr = shark.r;
                        int nd = shark.d;

                        move = shark.s % ((R - 1) * 2);

                        for (int i = 0; i < move; i++) {
                            if (nr == 0) {
                                nd = 2;
                            } else if (nr == R - 1) {
                                nd = 1;
                            }

                            nr += dir[nd][0];
                        }

                        shark.r = nr;
                        shark.d = nd;
                    }

                    if (newMap[shark.r][shark.c] == null) {
                        newMap[shark.r][shark.c] = shark;
                    } else {
                        if (newMap[shark.r][shark.c].z < shark.z) {
                            newMap[shark.r][shark.c] = shark;
                        }
                    }
                }
            }

            map = newMap;
        }

        System.out.println(answer);

        br.close();
    }
}
