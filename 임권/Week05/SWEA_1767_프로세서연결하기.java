package Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CORE {
    int r, c, t;

    public CORE(int r, int c, int t) {
        this.r = r;
        this.c = c;
    }
    
}

public class SWEA_1767_프로세서연결하기 {
    static int N, maxCount, minLen;
    static boolean[][] map;
    static boolean[][] mapCopy;
    static List<CORE> core = new ArrayList<>();
    static CORE[] sel;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            maxCount = 0;
            minLen = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            map = new boolean[N][N];

            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++) {
                    if(Integer.parseInt(st.nextToken()) == 1) {
                        map[r][c] = true;
                        core.add(new CORE(r, c, 0));
                    }
                }
            }
            sel = new CORE[core.size()];
            boolean[] v = new boolean[sel.length];
            permutation(0, v);

            System.out.println("#" + t + " " + minLen);
        }
    }

    //프로세스가 연결될 수 있도록 최단 거리 찾기
    static int connectBfs(CORE a) {
        int result = 0;
        CORE des = new CORE(0, 0, 0);
        ArrayDeque<CORE> q = new ArrayDeque<>();
        int[][] path = new int[N][N];
        q.offer(a);
        L : while(!q.isEmpty()) {
            CORE t = q.poll();

            for(int d = 0; d < dr.length; d++) {
                int rr = t.r + dr[d];
                int cc = t.c + dc[d];
                
                
                if(rr >= N || rr < 0 || cc >= N || cc < 0) {
                    des = t;
                    break L;
                } else if(mapCopy[rr][cc] || path[rr][cc] != 0) continue;

                
                path[rr][cc] = t.t + 1;
                q.offer(new CORE(rr, cc, t.t + 1));
            }
        }

        if(des.r > 0 && des.r < N - 1 && des.c > 0 && des.c < N - 1) return 0;

        result = des.t;

        int r = des.r, c = des.c;
        mapCopy[r][c] = true;
        for(int i = 0; i < des.t; i++) {
            for(int d = 0; d < dr.length; d++) {
                int rr = r + dr[d];
                int cc = c + dc[d];
                if(path[rr][cc] + 1 == path[r][c]) {
                    r = rr;
                    c = cc;
                    mapCopy[rr][cc] = true;
                    break;
                }
            }
        }

        print(path);
        return result;
    }

    static void permutation(int idx, boolean[] v) {
        if(idx == core.size()) {
            copyMap();
            int sum = 0, count = 0;
            for(int j = 0; j < idx; j++) {
                int tmp = connectBfs(sel[j]);
                if(tmp != 0) {
                    sum += tmp;
                    count++;
                }
            }
            if(count > maxCount) {
                maxCount = count;
                minLen = sum;
            } else if(count == maxCount && minLen > sum) {
                minLen = sum;
            }
            return;
        }

        for(int i = 0; i < core.size(); i++) {
            if(!v[i]) {
                sel[idx] = core.get(i);
                v[i] = true;
                permutation(idx + 1, v);
                v[i] = false;
            }
        }
    }

    static void copyMap() {
        mapCopy = new boolean[N][];
        for(int r = 0; r < map.length; r++) {
            mapCopy[r] = map[r].clone();
        }
    }

    static void print(int[][] m) {
        System.out.println();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
 */