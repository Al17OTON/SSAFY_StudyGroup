import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//땅 . 0
//빙하 x 1
//사람 L 9
public class Main {
    static class Swan {
        int x, y;
        public Swan(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br;
    static StringTokenizer st;
    private static int n, m;
    private static Swan[] swans;
    private static int[][] arr;
    private static Queue<Swan> swan_q, water_q;
    private static boolean[][] v;
    private static final int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    private static int day = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        v = new boolean[n][m];
        swans = new Swan[2];        // 백조의 좌표

        swan_q = new LinkedList<>();       //
        water_q = new LinkedList<>();

        int idx = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                char w = line.charAt(j);
                int num = w == 'L' ? 9 : w == 'X' ? 1 : 0;
                arr[i][j] = num;
                if (num == 9) {
                    swans[idx++] = new Swan(i, j);
                }
                if (num != 1) {
                    water_q.offer(new Swan(i, j));
                }
            }
        }
//      백조가 처음 있는 자리부터 시작
        swan_q.offer(swans[0]);
        v[swans[0].x][swans[0].y] = true;
        bfs();
        System.out.println(day);
    }




    private static void bfs() {
        boolean flag = false;
        while (true) {
            Queue<Swan> nq = new LinkedList<>();    //새로운 백조의 좌표를 저장
            while (!swan_q.isEmpty()) {
                Swan p = swan_q.poll();
                if (p.x == swans[1].x && p.y == swans[1].y) {       // 백조를 만났을때 종료
                    flag = true;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dir[i][0];
                    int ny = p.y + dir[i][1];
                    if (nx >= n || nx < 0 || ny >= m || ny < 0 || v[nx][ny]){
                        continue;
                    }
                    v[nx][ny] = true;
                    if (arr[nx][ny] == 1) {     // 빙하를 만났다면 그 부분부터 다시 탐색하도록 값 넣어줌 -> 새로운 백조의 좌표로 큐에 추가
                        nq.offer(new Swan(nx, ny));
                        continue;
                    }
                    swan_q.offer(new Swan(nx, ny)); // 백조의 좌표
                }
            }
            if (flag) {
                break;
            }
            swan_q = nq;
            meltBfs();          // 얼음 녹이기
            day++;
        }
    }

    private static void meltBfs() {
        int waterSize = water_q.size();
        for (int i = 0; i < waterSize; i++) {
            Swan p = water_q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dir[d][0];
                int ny = p.y + dir[d][1];
                if (nx >= n || nx < 0 || ny >= m || ny < 0) {
                    continue;
                }
                if (arr[nx][ny] == 1) {
                    arr[nx][ny] = 0;
                    water_q.offer(new Swan(nx, ny)); // 녹은 빙하의 좌표를 큐에 추가
                }
            }
        }
    }
}