package jiu;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int n = 9;
    static int[][] arr = new int[n][n];
    static boolean flag;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        print();
        dfs(0,0);   // depth, 가로
    }

    private static void dfs(int x, int y) {
        if(flag){
            return;
        }
        if(y == n){     // 가로 끝까지 도달했다면
            dfs(x+1,0);
            return;
        }

        if(x == n){     // 정답 부분 flag를 통해 탈출
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            flag = true;
            return;

        }

        if(arr[x][y] == 0){
            for (int k = 1; k <= n; k++) {
                if(check(x,y,k)){
                    arr[x][y] = k;
                    dfs(x,y+1);
                    arr[x][y] = 0;
                }
            }
            return;
        }
        dfs(x,y+1);
    }

    private static boolean check(int x, int y, int k) {
        for (int i = 0; i < n; i++) {           // 세로줄 검사
            if(arr[i][y] == k){
                return false;
            }
        }

        for (int i = 0; i < n; i++) {           // 가로줄 검사
            if(arr[x][i] == k){
                return false;
            }
        }


        //3*3 박스 검사
        int dx = x/3*3;
        int dy = y/3*3;

        for (int i = dx; i < dx+3; i++) {
            for (int j = dy; j < dy+3; j++) {
                if(arr[i][j] == k){
                    return false;
                }
            }
        }
        return true;
    }

    private static void print() {
        for (int[] lst : arr) {
            System.out.println(Arrays.toString(lst));
        }
    }
}
