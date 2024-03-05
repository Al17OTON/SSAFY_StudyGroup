package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노v1 {
    
    static int R, C, maxSum = -1;
    static int[][] map;
    static int[][][] v;         //0 : 좌, 1 : 우, 2 : 상, 3 : 하

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int[] check = {1, 0, 3, 2};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        v = new int[R][C][4];

        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                int pre_c = c - 1;
                int pre_r = r - 1;
                if(pre_c >= 0) {
                    v[r][c][0] = map[r][c] + map[r][pre_c];
                    v[r][pre_c][1] = map[r][c] + map[r][pre_c];
                }
                if(pre_r >= 0) {
                    v[pre_r][c][3] = map[r][c] + map[pre_r][c];
                    v[r][c][2] = map[r][c] + map[pre_r][c];
                }
            }
        }

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                maxSum = Math.max(maxSum, getValue(r, c, 0));
                maxSum = Math.max(maxSum, getValue(r, c, 1));
                maxSum = Math.max(maxSum, getValue(r, c, 2));
                maxSum = Math.max(maxSum, getValue(r, c, 3));
            }
        }

        System.out.println(maxSum);

    }

    static int getValue(int r, int c, int dir) {
        int sum = 0;
        for(int i = 0; i < dr.length; i++) {
            if(i != dir) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= R || nr < 0 || nc >= C || nc < 0) continue;

                for(int j = 0; j < 4; j++) {
                    if(check[j] != i) {
                        sum = Math.max(sum, v[r][c][dir] + v[nr][nc][j]);
                    }
                }
            }
        }

        // ㅜ, ㅗ 모양
        if(dir == 0) {
            if(c + 1 < C) {
                int tmp = v[r][c][dir] + map[r][c + 1];
                //상하값만 넣어주면됨
                if(r + 1 < R) sum = Math.max(sum, tmp + map[r + 1][c]);
                if(r - 1 >= 0) sum = Math.max(sum, tmp + map[r - 1][c]);
            }
        //ㅏ ㅓ 모양
        } else if(dir == 2) {
            if(r + 1 < R) {
                int tmp = v[r][c][dir] + map[r + 1][c];
                //좌우값만 넣어주면됨
                if(c + 1 < C) sum = Math.max(sum, tmp + map[r][c + 1]);
                if(c - 1 >= 0) sum = Math.max(sum, tmp + map[r][c - 1]);
            }
        }

        return sum;
    }
    
}
