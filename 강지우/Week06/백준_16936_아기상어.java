package org.example.jiu;//package jiu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 좌표와 몇번째인지 확인할 depth 필요
// 물고기의 개수를 세자
// 재풀이 해보자
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, shark = 2,fish, x, y, cnt = 0, ans = 0;
    static int[][] arr;
    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        fish = 0;
        
//      입력받을때 상어의 위치와 먹이의 수 저장
//      상어위치 저장했으니 0으로 만들자
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9){
                    x = i;
                    y = j;
                    arr[i][j] = 0;
                }else if(arr[i][j] > 0){
                    fish++;
                }
            }
        }

//      먹이가 0일때까지 반복하다가 bfs 돌린뒤에 tmp 의 값과 일치한다면 먹이를
//      못먹은거 -> 종료
        int tmp;        // 먹이 임시저장 변수
        while(fish != 0){
            tmp = fish;
            bfs();
            if(tmp == fish){
                break;
            }
        }
        System.out.println(ans);
    }

    private static void bfs() {
        boolean[][] v = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y,1});       // 상어의 좌표와 얼마나 같는지 저장
        v[x][y] = true;

//      초기값 세팅
        int n_x = -1;
        int n_y = -1;
        int n_depth = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            int[] p = q.poll();
            int dx = p[0];
            int dy = p[1];
            int depth = p[2];

//              다음 물거기 , 탐색 위치 비교해서 탐색 위치가 더 멀면 종료
            if(n_depth < depth){
                break;
            }
            
            for (int[] d : dir) {
                int nx = dx + d[0];
                int ny = dy + d[1];

//              상어의 크기보다 작은곳만 이동 가능
                if(nx>=0&&nx<n&&ny>=0&&ny<n&&!v[nx][ny]&&arr[nx][ny]<=shark) {
                    // 먹이의 크기가 작고 물고기가 있다면
                    if(arr[nx][ny] > 0 && shark>arr[nx][ny]){
//                      뭐 먹을지 아직 모름
                        if(n_x == -1){
                            n_x = nx;
                            n_y = ny;
                            n_depth = depth;
                        } else{         
                            // 현재 위치가 다음 물고기보다 위나 왼쪽일 경우 다음 물고기 변경
//                             다시보기
                            if(n_x > nx){
                                n_x = nx;
                                n_y = ny;
                            }else if(n_x == nx){
                                if(n_y > ny){
                                    n_x = nx;
                                    n_y = ny;
                                }
                            }
                        }
                    }
                    v[nx][ny] = true;
                    q.add(new int[]{nx,ny, depth+1});
                }
            }
        }

//      먹기 가능
        if(n_x != -1){
            arr[n_x][n_y] = 0;
            ans += n_depth;
            fish--;
            x = n_x;
            y = n_y;
            cnt++;
            if(cnt == shark){
                cnt = 0;
                shark++;
            }
        }


    }

//        print(arr);

    private static void print(int[][] arr) {
        for (int[] lst : arr) {
            System.out.println(Arrays.toString(lst));
        }
        System.out.println();
    }
}