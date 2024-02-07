package Week02;

import java.util.Scanner;

public class SWEA_4615_재미있는오셀로게임 {
    
    static int black, white;
    static char[][] map; 
    static int N, M;
    static int[] dirX = {-1, 1, 0, 0, -1, -1, 1, 1};     //상하좌우 왼위 오위 왼아 오아 8방탐색 
    static int[] dirY = {0, 0, -1, 1, -1, 1, -1, 1};
    
    static boolean flip(int dir, int x, int y, char color) {

        if(x >= N || x < 0 || y >= N || y < 0 || map[x][y] == '\0') return false;    //맵 밖으로 나가거나 빈공간이라 뒤집기 불가하면 false, char 의 기본값은 '\0'인것을 명심하자

        if(map[x][y] == color) {    //같은 색 돌을 만나면 뒤집기시작할 수 있게 true 반환
            return true;
        }
        if(flip(dir, x + dirX[dir], y + dirY[dir], color)) {    //재귀, 끝에 같은 색 돌을 만나면 뒤집기
            map[x][y] = color;
            if(color == 'B') {
                black++;
                white--;
            } else {
                black--;
                white++;
            }

            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            black = 2; white = 2;
            N = sc.nextInt();
            M = sc.nextInt();

            map = new char[N][N];
            map[N / 2 - 1][N / 2 - 1] = 'W';
            map[N / 2 - 1][N / 2] = 'B';
            map[N / 2][N / 2 - 1] = 'B';
            map[N / 2][N / 2] = 'W';

            for(int m = 0; m < M; m++) {
                int y = sc.nextInt() - 1;
                int x = sc.nextInt() - 1;
                char c = sc.nextInt() == 1 ? 'B' : 'W';
                map[x][y] = c;
                if(c == 'B') black++;
                else white++;

                for(int i = 0; i < 8; i++) {    //8방탐색
                    flip(i, x + dirX[i], y + dirY[i], c);
                }
                if(white + black == N * N) break;
            }
            
            System.out.println("#" + t + " " + black + " " + white);
        }

    }
}
