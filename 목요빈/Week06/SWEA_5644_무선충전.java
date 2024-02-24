package 목요빈.Week06;

import java.io.*;
import java.util.*;
 
public class SWEA_5644_무선충전 {
 
    static int M, N, max;
    static Pos[] BC;
    static int[][] board = new int[10][10];
    static int[] A, B, dr = { 0, -1, 0, 1, 0 }, dc = { 0, 0, 1, 0, -1 };
    // 모든 사용자가 충전한 양의 합의 최댓값
 
    static class Pos implements Comparable<Pos>{
        int r, c, range, p, num;
 
        public Pos(int c, int r) {
            super();
            this.r = r;
            this.c = c;
        }
         
        public Pos(int p, int num, boolean flag) {
            super();
            this.p = p;
            this.num = num;
        }
 
        public Pos(int c, int r, int range, int p) {
            super();
            this.r = r;
            this.c = c;
            this.range = range;
            this.p = p;
        }
 
        @Override
        public int compareTo(Pos o) {
            return o.p - this.p;
        }
         
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 총 이동시간
            N = Integer.parseInt(st.nextToken()); // 충전기 개수 <= 8
 
            A = new int[M];
            B = new int[M];
            BC = new Pos[N];
            max = 0;
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                BC[i] = new Pos(Integer.parseInt(st.nextToken())-1, // r
                        Integer.parseInt(st.nextToken())-1, // c
                        Integer.parseInt(st.nextToken()), // range
                        Integer.parseInt(st.nextToken())); // performance
            }
             
            System.out.println("#" + test_case + " " + ㅠㅠ());
        }
    }
     
    static int ㅠㅠ() {
        Pos a = new Pos(0, 0);
        Pos b = new Pos(9, 9);
        int cnt = 0;
         
        for (int idx = 0; idx < M+1; idx++) {
            Pos[] a_pos = new Pos[N]; // Pos의 p, num만 활용
            Pos[] b_pos = new Pos[N];
 
            for (int i = 0; i < N; i++) {
                // a는 BC[i]의 범위 내
                if (distance(a, BC[i]) <= BC[i].range) {
                    a_pos[i] = new Pos(BC[i].p, i, true);
                } else a_pos[i] = new Pos(0, i, true);
 
                // b는 BC[i]의 범위 내
                if (distance(b, BC[i]) <= BC[i].range) {
                    b_pos[i] = new Pos(BC[i].p, i, true);
                } else b_pos[i] = new Pos(0, i, true);
            }
 
            Arrays.sort(a_pos);
            Arrays.sort(b_pos);
 
            if(idx != M) {
                a = new Pos(a.c + dc[A[idx]], a.r + dr[A[idx]]);
                b = new Pos(b.c + dc[B[idx]], b.r + dr[B[idx]]);
            }
                         
            if(a_pos[0].p == 0 && b_pos[0].p == 0) {
                continue;
            }
            // 아래 예외처리 !!!!!
            else if(a_pos[0].p == 0 && b_pos[0].p > 0) {
                cnt += b_pos[0].p;
            }else if(a_pos[0].p > 0 && b_pos[0].p == 0) {
                cnt += a_pos[0].p;
            }
             
            else if(a_pos[0].num != b_pos[0].num) {
                // 같은 bc가 아니면
                cnt += a_pos[0].p + b_pos[0].p;
            }else {
                // 첫 BC가 같은 BC이면  -> 무조건 첫 값은 같음
                // 두 충전기의 같을 때
                if(a_pos[1].p == 0 && b_pos[1].p == 0) {
                    cnt += a_pos[0].p;
                }
                else{
                    if(a_pos[1].p > b_pos[1].p) {
                        cnt += b_pos[0].p + a_pos[1].p;
                    }else if(a_pos[1].p <= b_pos[1].p) {
                        cnt += b_pos[1].p + a_pos[0].p;
                    }
                }
            }
        }
        return cnt;
    }
     
     
    static int distance(Pos a, Pos b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }
}
