//package jiu;

import java.io.*;
import java.util.*;

public class Solution {
    static class Bc{
        int r;
        int c;
        int number;

        public Bc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class User{
        int x;
        int y;

        public User(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class BcNum{
        int power;
        int number;
        public int getPower() {
            return power;
        }

        public int getNumber() {
            return number;
        }

        public BcNum(int power, int number) {
            this.power = power;
            this.number = number;
        }
    }

    static BufferedReader br;
    static StringTokenizer st;
    static int  t,m,a,ans;
    static int user_a_idx,user_b_idx;
    static int size = 10;
    static int[][] go,battery;	// 0번은 a의 이동 1번은 b의 이동
    static ArrayList<BcNum>[][] arr;

    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws NumberFormatException, IOException {
        //System.setIn(new FileInputStream("src/jiu/Solution.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()) +1;
            int a = Integer.parseInt(st.nextToken());
            newArr();

            go = new int[2][m];
            battery = new int[a][4];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j < m; j++) {
                    go[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    battery[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Arrays.sort(battery, Comparator.comparingInt((int[] bt) -> bt[3]).reversed());
            for (int i = 0; i < a; i++) {
                int x = battery[i][0];
                int y = battery[i][1];
                int c = battery[i][2];
                int p = battery[i][3];
                flood_fill(y-1,x-1,c,p, i);
            }

            ans = 0;

            User user_a = new User(0,0);
            User user_b = new User(size-1,size-1);

            for (int i = 0; i < m; i++) {
                int a_num = 0;
                int b_num = 0;
                int user_a_go = go[0][i];
                int user_b_go = go[1][i];
                int nxa = user_a.x + user_dir[user_a_go][0];
                int nya= user_a.y + user_dir[user_a_go][1];
                int nxb = user_b.x + user_dir[user_b_go][0];
                int nyb = user_b.y + user_dir[user_b_go][1];
                int a_len = arr[nxa][nya].size();
                int b_len = arr[nxb][nyb].size();

                if(a_len != 0 && b_len != 0) {
                    BcNum abc = arr[nxa][nya].get(0);
                    BcNum bbc = arr[nxb][nyb].get(0);
                    if(abc.power == bbc.power) {
                        if(abc.number != bbc.number) {
                            a_num = abc.power;
                            b_num = bbc.power;
                        }else if(a_len > 1 && b_len == 1) {
                            a_num = arr[nxa][nya].get(1).power;
                            b_num = arr[nxb][nyb].get(0).power;
                        }else if(a_len == 1 && b_len > 1) {
                            a_num = arr[nxa][nya].get(0).power;
                            b_num = arr[nxb][nyb].get(1).power;
                        }else if(a_len > 1 && b_len>1) {
                            if(arr[nxa][nya].get(1).power > arr[nxb][nyb].get(1).power){
                                a_num = arr[nxa][nya].get(1).power;
                                b_num = arr[nxb][nyb].get(0).power;
                            }else if(arr[nxa][nya].get(1).power <= arr[nxb][nyb].get(1).power){
                                a_num = arr[nxa][nya].get(0).power;
                                b_num = arr[nxb][nyb].get(1).power;
                            }
                        }
                        else if(abc.number == bbc.number){	// 같은 배터리를 공유하고 있는 경우라면
                            ans += abc.power;
                        }
                    }else {// 하나는 사이즈가 없고 하나는 있을때 ->
                        a_num = abc.power;
                        b_num = bbc.power;
                    }
                }else if(a_len == 0 && b_len != 0) {
                    b_num = arr[nxb][nyb].get(0).power;
                }else if(a_len != 0 && b_len == 0) {
                    a_num = arr[nxa][nya].get(0).power;
                }

                ans += a_num + b_num;
                user_a = new User(nxa,nya);
                user_b = new User(nxb,nyb);
            }
            System.out.printf("#%d %d\n",tc,ans);
        }// end
    }
    static int[][] user_dir = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
    @SuppressWarnings("unchecked")
    private static void newArr() {
        arr = new ArrayList[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }
    }

    //	좌표, 배터리의 범위, 출력
    private static void flood_fill(int x, int y, int c, int p, int i) {
        int tmp[][] = new int[size][size];
        int cnt = 0;
        Queue<Bc> q = new LinkedList<>();
        tmp[x][y] = 1;
        arr[x][y].add(new BcNum(p,i));
        q.offer(new Bc(x,y));
        while(!q.isEmpty()) {
            Bc bc = q.poll();
            if(tmp[bc.r][bc.c] == c+1) {
                break;
            }

            for (int[] d : dir) {
                int nx = bc.r + d[0];
                int ny = bc.c + d[1];

                if(nx<0 || nx>=size || ny<0 || ny>=size) {
                    continue;
                }

                if(tmp[nx][ny] != 0) {
                    continue;
                }

                tmp[nx][ny] = tmp[bc.r][bc.c] + 1;
                arr[nx][ny].add(new BcNum(p,i));
                if(tmp[nx][ny] > cnt) {
                    cnt = tmp[nx][ny];
                }
                q.offer(new Bc(nx,ny));
            }
        }
    }
}