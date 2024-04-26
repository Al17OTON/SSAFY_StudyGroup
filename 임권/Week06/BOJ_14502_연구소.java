package Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Virus {   //바이러스 위치 정보
    int r, c;

    public Virus(int r, int c) {
        this.r = r;
        this.c = c;
    }

}

public class BOJ_14502_연구소 {
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};    //상하좌우
    static int[] dc = {0, 0, -1, 1};
    static List<Virus> virus;
    static int safeZone;
    static int maxSafe = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        virus = new ArrayList<>();      //바이러스 위치 기억 리스트
        safeZone = 0;                   //전염시작전 안전구역 수

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 2) {
                    virus.add(new Virus(r, c));
                } else if(map[r][c] == 0) { //안전구역 카운트
                    safeZone++;
                }
            }
        }

        safeZone -= 3;      //벽을 어딘가에 무조건 3개 세워야하므로 안전구역은 그만큼 줄어든다.

        //벽 3개 세우기
        for(int r1 = 0; r1 < N; r1++) {
            for(int c1 = 0; c1 < M; c1++) {

                if(map[r1][c1] != 0) continue;  //0이 아닌 곳에는 벽을 세울 수 없다
                map[r1][c1] = 1;                

                for(int r2 = r1; r2 < N; r2++) {
                    for(int c2 = 0; c2 < M; c2++){
                        
                        if(map[r2][c2] != 0) continue;
                        map[r2][c2] = 1;

                        for(int r3 = r2; r3 < N; r3++) {
                            for(int c3 = 0; c3 < M; c3++) {

                                if(map[r3][c3] != 0) continue;
                                map[r3][c3] = 1;

                                int safe = safeZone;
                                boolean[][] vv = new boolean[N][M];     //감염여부 표시
                                for(int i = 0; i < virus.size(); i++) { //각 바이러스 위치에서 dfs로 전염을 시작한다.
                                    Virus v = virus.get(i);
                                    safe -= spreadVirusDfs(v.r, v.c, vv);  //dfs는 전염한 구역 수를 반환한다.
                                }

                                maxSafe = Math.max(maxSafe, safe);
                                map[r3][c3] = 0;
                            }
                        }

                        map[r2][c2] = 0;        //원상복구
                    }
                }
                map[r1][c1] = 0;
            }
        }

        System.out.println(maxSafe);
    }

    static int spreadVirusDfs(int r, int c, boolean[][] v) {
        int infected = 0;   //감염 영역

        //4방으로 퍼져나간다.
        for(int d = 0; d < dr.length; d++) {
            int rr = r + dr[d];
            int cc = c + dc[d];

            if(rr >= N || rr < 0 || cc >= M || cc < 0 || map[rr][cc] != 0 || v[rr][cc]) continue; 
            v[rr][cc] = true;
            infected++;                             //감염시켰다면 감염 수 증가
            infected += spreadVirusDfs(rr, cc, v);  //감염된 구역에서 감염 시작하기
        }

        return infected;                            //전체 감염값 반환
    }
}
