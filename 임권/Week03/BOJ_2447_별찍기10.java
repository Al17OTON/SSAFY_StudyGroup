package Week03;

import java.util.Scanner;

public class BOJ_2447_별찍기10 {
    static int N;
    static boolean[][] map;
    static int[] dirX = {-1, 1, 0, 0, -1, -1, 1, 1}; //상하좌우 왼위 오위 왼아 오아
    static int[] dirY = {0, 0, -1, 1, -1, 1, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new boolean[N][N];

        setStar(N / 2, N / 2, N, true);

        StringBuilder sb = new StringBuilder(); //스트링 빌더 안쓰면 시간초과남..., 거기다 String에 계속 값 대입하면 메모리 초과도 뜰수있음...
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(map[i][j] ? "*":" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void setStar(int x, int y, int lev, boolean isStar) {
        if(lev == 1) {
            map[x][y] = isStar;
            return;
        } 
        //if(!isStar) {  //가지치기, 중앙이라면 다 빈칸으로 채우기, boolean 배열이기때문에 for문으로 초기화할 필요가 없다.
        //    return;
        //}

        lev /= 3;

        for(int i = 0; i < dirX.length; i++) {  //8방탐색
            setStar(x + dirX[i] * lev, y + dirY[i] * lev, lev, isStar);
        }

        //setStar(x, y, lev, false);  //중앙은 빈칸

    }
}