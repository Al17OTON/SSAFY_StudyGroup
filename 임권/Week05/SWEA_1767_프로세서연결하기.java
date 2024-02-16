package Week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CORE {
    int r, c;

    public CORE(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class SWEA_1767_프로세서연결하기 {
    static int N, maxCore, minLen;
    static boolean[][] map;
    static List<CORE> core;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            core = new ArrayList<>();
            maxCore = 0;
            minLen = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            map = new boolean[N][N];
            
            for(int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < N; c++) {
                    if(st.nextToken().equals("1")) {
                        map[r][c] = true;
                        
                        //만약 가장자리에 붙어있는 코어라면 연산에서 제외하기
                        if(r == 0 || r == N - 1 || c == 0 || c == N - 1) continue;
                        core.add(new CORE(r, c));
                    }
                }
            }

            dfs(0, 0, 0);

            System.out.println("#" + t + " " + minLen);

        }
    }
    
    //모든 코어가 최대로 연결될 수 있는 최소 거리 찾기
    static void dfs(int idx, int len, int coreCount) {
        if(idx == core.size()) {
            //가장 많은 코어를 연결한 값으로 업데이트
            if(coreCount > maxCore) {
                maxCore = coreCount;
                minLen = len;

            //만약 연결된 코어수가 같다면 최소 길이로 업데이트
            } else if(coreCount == maxCore) {
                minLen = Math.min(minLen, len);
            }
            return;
        }

        CORE co = core.get(idx);
        for(int i = 0; i < dr.length; i++) {
            //전선 연결, 연결에 성공하면 0이 아닌 수가 반환되어 결과를 알 수 있다.
            int res = makeLine(co.r + dr[i], co.c + dc[i], i, 1);

            //연결못한 경우도 다른 코어를 연결하러 감
            if(res == 0) dfs(idx + 1, len, coreCount);
            else {
                dfs(idx + 1, len + res, coreCount + 1);
                //원상복구
                deleteLine(co.r + dr[i], co.c + dc[i], i);
            }
        }
    }

    //주어진 방향으로 전선연결하기. 만약 장애물을 만나면 전선을 원상복구한다.
    static int makeLine(int r, int c, int dir, int len) {
        //다른 선이나 코어를 만나면 실패
        if(map[r][c]) {
            return 0;
        }
        //만약 끝에 도달하면 현재까지의 길이 반환
        if(r == N - 1 || r == 0 || c == N - 1 || c == 0) {
            return len;
        }

        //전선설치
        map[r][c] = true;

        //다음 전선설치하러가기
        int tmp = makeLine(r + dr[dir], c + dc[dir], dir, len + 1);
        
        //만약 0이 아니라면 종료 
        if(tmp != 0) return tmp;

        //0이 반환되었다면 전선 복구
        map[r][c] = false;

        return 0;
    }

    //주어진 방향에 있는 전선 제거하기
    static void deleteLine(int r, int c, int dir) {
        if(r == N - 1 || r == 0 || c == N - 1 || c == 0) return;

        map[r][c] = false;
        deleteLine(r + dr[dir], c + dc[dir], dir);
    }
}

/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
*/

