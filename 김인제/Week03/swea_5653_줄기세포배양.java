import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* SWEA 5653. [모의 SW 역량테스트] 줄기세포배양 */
public class Problem5653 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input/swea/Input5653.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int T = Integer.parseInt(br.readLine());

        boolean flag;
        int N, M, K, vit, time, nx, ny, answer;
        int[][][] grid;

        for (int tc = 1; tc < T + 1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());


            if (K % 2 == 0) {
                grid = new int[N + K][M + K][7];
            } else {
                grid = new int[N + K - 1][M + K - 1][7];
            }

            for (int i = K / 2; i < N + (K / 2); i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = K / 2; j < M + (K / 2); j++) {
                    vit = Integer.parseInt(st.nextToken());

                    if (vit != 0) {
                        grid[i][j][0] = 1; // 생존여부
                        grid[i][j][1] = 0; // 활성화여부
                        grid[i][j][2] = 0; // 생성시간 (0초)
                        grid[i][j][3] = vit; // 활성화남은시간  (== 생명력)
                        grid[i][j][4] = 1; // 존재여부
                        grid[i][j][5] = vit; // 생명력
                        grid[i][j][6] = vit; // 남은생명  (== 생명력)
                    }
                }
            }

            flag = true; // 생존한 세포 확인
            time = 0; // 시간 확인

            while (flag && ++time <= K) { // 생존한 세포가 없거나 시간이 되기 전까지 반복
                flag = false; // 세포 생존 확인 초기화

                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j][0] == 1) { // 세포가 생존 중이면
                            flag = true; // 생존한 세포가 존재

                            if (grid[i][j][1] == 0) { // 활성화되지 않았다면
                                if (grid[i][j][2] != time && --grid[i][j][3] == 0) { // 방금 생성된 것이 아니고 활성화까지 남은 시간이 0이면
                                    grid[i][j][1] = 1; // 세포 활성화
                                }
                            } else { // 활성화되어 있다면
                                for (int k = 0; k < 4; k++) {
                                    nx = i + dir[k][0];
                                    ny = j + dir[k][1];

                                    if ((nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[i].length) // ArrayOutOfRange 예외처리
                                            && (grid[nx][ny][4] == 0 // 해당 칸이 빈칸이거나
                                                || (grid[nx][ny][2] == time && grid[i][j][5] > grid[nx][ny][5]))) { // 방금 생성된 세포이면서 생명력이 현재 세포보다 낮다면
                                        grid[nx][ny][0] = 1; // 생존여부
                                        grid[nx][ny][1] = 0; // 활성화여부
                                        grid[nx][ny][2] = time; // 생성시간 (현재 시간)
                                        grid[nx][ny][3] = grid[i][j][5]; // 활성화남은시간 (== 생명력)
                                        grid[nx][ny][4] = 1; // 존재여부
                                        grid[nx][ny][5] = grid[i][j][5]; // 생명력 (생명력이 높은 세포가 차지)
                                        grid[nx][ny][6] = grid[i][j][5]; // 남은생명 (== 생명력)
                                    }
                                }

                                if (--grid[i][j][6] == 0) { // 생명력 감소 & 생명력이 0이면
                                    grid[i][j][0] = 0; // 사망처리
                                }
                            }
                        }
                    }
                }
            }

            answer = 0;

            for (int[][] x : grid) { 
                for (int[] xy : x) {
                    if (xy[0] == 1) { // 세포가 살아있으면
                        answer++; // + 1
                    }
                }
            }

            System.out.println("#"+ tc + " " + answer);
        }

        br.close();
    }
}
