package Week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Cell {
    int x, time, i, j;

    public Cell(int x, int time, int i, int j) {
        this.x = x;
        this.time = time;
        this.i = i;
        this.j = j;
    }
}

/*
    처음 구현할때는 단순한 구현 문제일 줄 알았는데 시간개념을 고려하면서 동시에 배양하는 경우 생명력이 더 강한 세포가 배양을 하게 되는 것도 고려해야하는 복잡한 문제였다.

    너무 어려워서 정답을 봤다.
    해답은 우선순위 큐를 사용하는 것이였다.
    1시간 동안 생명력이 높은 세포들만 우선적으로 배양하도록 하여 동시 배양문제도 해결할 수 있었다.
    
    우선순위큐에 죽지않은 세포들을 모두 넣은뒤 1시간마다 우선순위 큐를 비우면서 각 세포에 대한 연산을 수행한다. 생명력 순으로 정렬하였기 때문에 가장 생명력이 높은 세포들만 배양에 성공하게 된다.
    그리고 나서 죽지 않은 세포들은 전부 큐에 넣은 다음 1시간에 대한 연산이 끝나는 시점(우선순위큐가 비워졌을때)에 다시 우선순위 큐에 모두 넣어준다.
    이러한 연산을 시간이 다 될때 까지 반복하면 죽지않은 세포는 자연스럽게 우선순위 큐에 남아있으므로 이를 출력하면 정답이 된다. 
*/
public class SWEA_5653_줄기세포배양 {

    static int N, M, K;
    static boolean[][] v;
    static PriorityQueue<Cell> pq;
    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            pq = new PriorityQueue<>(
                (o1, o2) ->  o2.x - o1.x
            );
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            v = new boolean[N + K * 2][M + K * 2];      //세포가 있음을 표시, 배양판의 크기는 무한하므로 N + K * 2로 최대 배양판의 크기를 설정

            int tmp;
            for(int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for(int m = 0; m < M; m++) {
                    tmp = Integer.parseInt(st.nextToken());
                    if(tmp != 0) {
                        pq.add(new Cell(tmp, tmp, n + K, m + K));
                        v[n + K][m + K] = true;
                    }
                }
            }
            
            int ii, jj;
            Cell c;
            ArrayDeque<Cell> q = new ArrayDeque<>();
            for(int k = 0; k < K; k++) {
                
                while(!pq.isEmpty()) {
                    c = pq.poll();
                    c.time--;

                    if(c.time < 0) {                                //X 시간만큼 지나면 활성상태
                        for(int d = 0; d < dirX.length; d++) {
                            ii = c.i + dirX[d]; jj = c.j + dirY[d];
                            if(!v[ii][jj]) {
                                v[ii][jj] = true;
                                q.offer(new Cell(c.x, c.x, ii, jj));   
                            }
                        }
                    } 
                    if(c.time + c.x == 0) continue; //x시간이 지나 활성화되었고 또 x시간이 지나면 죽은 세포이므로 큐에 삽입하지 않기
                    q.push(c);
                }
                while(!q.isEmpty()) pq.add(q.poll());

            }

            System.out.println("#" + t + " " + pq.size());

        }
    }
}
