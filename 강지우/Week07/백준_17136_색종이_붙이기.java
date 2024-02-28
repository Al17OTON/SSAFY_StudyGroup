import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 큰 순서대로 지도의 크기만큼 전부 완탐
// 크기에 맞는 색종이를 붙였다 때엇다
//{0,5,5,5,5,5};
public class Main {
    static int n = 10;
    static BufferedReader br;
    static StringTokenizer st;
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[] paper = {0,5,5,5,5,5};     //1,2,3,4,5
    static final int SIZE = 5;
    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[n][n];        //10 x 10 배열 생성 - 최대크기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//		print(map);
        rc(0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
    private static void rc(int cnt) {
        // 색종이를 붙여야 하는 영역찾기
        int dx = -1, dy = -1;

        L:for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1) {        // 붙일수 있다면
                    dx = i;
                    dy = j;
                    break L;
                }
            }
        }

        // 색종이가 다 붙여 졌으면
        if(dx == -1 && dy == -1) {
            ans = Math.min(ans, cnt);
            return ;
        }

        // dx ,dy 기준 색종이 최대크기 구하기
        int size = getPageSize(dx,dy);

        if(size == -1) {
            return;
        }

        for (int i = 1; i <= size; i++) {
            // 색종이가 있다면
            if(paper[i] > 0) {
                for (int j = 0; j < i; j++) {       // i번째 색종이 부착
                    for (int z = 0; z < i; z++) {
                        map[dx+j][dy+z] = 0;
                    }
                }
                paper[i]--;
                rc(cnt+1);

                for (int r = 0; r < i; r++) {       // i번째 색종이 원복
                    for (int c = 0; c < i; c++) {
                        map[dx+r][dy+c] = 1;
                    }
                }
                paper[i]++;
            }
        }
    }

    private static int getPageSize(int dx, int dy) {
        // 큰 색종이 부터 부착
        for (int size = SIZE; size > 0; size--) {
            boolean flag = true;
            L:for (int i = dx; i < dx+size; i++) {      // 주어진 좌표에 size 만큼 지도 탐색
                for (int j = dy; j < dy+size; j++) {
                    if(i<0 || i>= n || j<0 || j>= n || map[i][j] == 0) {
                        flag = false;
                        break L;
                    }
                }
            }

            if(flag) {
                return size;
            }
        }
        return -1;
    }

    private static void print(int[][] map) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
