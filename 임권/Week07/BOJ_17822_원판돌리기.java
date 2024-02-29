package Week07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


//DFS로 풀면 시간초과난다 BFS로 풀어야한다.
//너무 어려운 구현문제인거 같다.
//문제도 설명이 너무 부실해서 예제를 잘 봐야한다.

//회전 연산을 각 원판의 first 변수를 움직이도록 해서 회전을 표현했다.
//그리고 bfs로 각 원판의 인접한 숫자들을 찾는데 이때 각 원판은 0을 기준으로하는 절대 좌표를 받으면
//first를 더해 자신의 상대 좌표로 변환하여 값을 반환한다.
//이떄 반환된 값이 같다면 제거용 큐에 넣고 탐색이 끝났을때 다 삭제한다.
//문제에서 설명되지 않는 규칙은 다음과 같다.
//1. 원판이 회전되었을때 제거되는 숫자는 모든 원판에 해당한다. == 모든 요소를 탐색
//2. 원판이 회전되었을때 제거될 숫자가 없다면 평균을 통해 +/- 1을 하는데 이때 평균은 모든 원판의 요소값 / 모든 원판의 남은 요소 수

public class BOJ_17822_원판돌리기 {

    static class Round {
        //0 - clock, 1 - semi clock
        static int[] dir = {-1, 1};
        static int sum, cnt;
        int no, first;
        int[] nums;

        static {
            sum = 0;
            cnt = N * M;
        }
        public Round(int no, int size) {
            this.no = no;
            this.first = 0;
            // this.sum = 0;
            // this.cnt = size;
            nums = new int[size];
        }
        void setNum(int i, int data) {
            nums[i] = data;
            sum += data;
        }

        void rotate(int d, int len) {
            len *= dir[d];
            first += len;

            if(first < 0) {
                first = nums.length + first;
            } else {
                first %= nums.length;
            }
        }

        //절대 좌표 기준으로 주어진 i 값을 first만큼 더해 제거하기
        void remove(int i) {
            if(nums[(i + first) % nums.length] == 0) return;

            sum -= nums[(i + first) % nums.length];
            nums[(i + first) % nums.length] = 0;
            cnt--;
        }

        int get(int i) {
            return nums[(i + first) % nums.length];
        }

        //평균보다 크면 -1 작으면 +1
        void add_sub() {
            // float avg = getAvg();

            if(avg == 0) return;

            for(int i = 0; i < nums.length; i++) {
                if(nums[i] != 0 && nums[i] != avg) {
                    int var = (nums[i] > avg) ? -1 : 1;
                    nums[i] += var;
                    sum += var;
                }
            }
        }
        
        static float getAvg() {
            if(cnt == 0) return 0;
            return (float)sum / (float)cnt;
        }
        

    }

    static int N, M, T;
    static Round[] rounds;
    static Queue<int[]> removeQ = new LinkedList<>();
    static float avg;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        rounds = new Round[N];

        for(int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            rounds[n - 1] = new Round(n, M);
            for(int m = 0; m < M; m++) rounds[n - 1].setNum(m, Integer.parseInt(st.nextToken()));
        }

        for(int t = 0; t < T; t++) {
            // System.out.println("#####"+t+"#####");
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for(int i = x - 1; i < N; i += x) {
                rounds[i].rotate(d, k);
            }
            // System.out.println("#####회전 후#####");
            // print();

            //check(0, 0, new boolean[N][M]);
            //checkBfs();
            check();

            if(removeQ.isEmpty()) {
                avg = Round.getAvg();
                // System.out.println(avg);
                for(int i = 0; i < N; i++) rounds[i].add_sub();
            } else {
                while(!removeQ.isEmpty()) {
                    int[] tmp = removeQ.poll();

                    rounds[tmp[0]].remove(tmp[1]);
                }
            }
            // System.out.println("#####변경 후#####");
            // print();
        }

        System.out.println(Round.sum);
    }

    static void check() {
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			
    			int me = rounds[i].get(j);
                if(me == 0) continue;
    			
    			for(int d = 0; d < dr.length; d++) {
    				int nr = i + dr[d];
    				int nc = j + dc[d];
    				
    				if(nr >= N || nr < 0) continue;
    				
    				if(nc < 0) nc = M + nc;
    				else nc %= M;
    				
    				if(me == rounds[nr].get(nc)) {
    					removeQ.offer(new int[] {i, j});
    					break;
    				}
    			}
    		}
    	}
    }

    static void checkBfs() {
        boolean[][] v = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        v[0][0] = true;

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            boolean flag = false;

            for(int i = 0; i < dr.length; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];

                if(nc < 0) nc = M + nc;
                else nc %= M;

                if(nr >= N || nr < 0) continue;

                //주변에 하나라도 같은 값이 있다면 제거할 목록에 추가
                if(!flag && rounds[tmp[0]].get(tmp[1]) != 0 && rounds[tmp[0]].get(tmp[1]) == rounds[nr].get(nc)) {
                    flag = true;
                    removeQ.offer(new int[] {tmp[0], tmp[1]});
                }

                if(!v[nr][nc]) {
                    v[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
    }

    static void print() {
        System.out.println("     - " + Round.cnt + "    " + Round.sum);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(rounds[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}
