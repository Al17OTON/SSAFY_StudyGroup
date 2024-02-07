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

/*

package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Cell {
	int x, cnt = 1, time;	//x는 세포의 생명력 수치, cnt는 현재 주기 위치, time은 세포가 어느 시점에 번식되었는지 기록
	boolean active = false;	//현재 활성화 상태인지
	boolean isdead = false;
	
	Cell(int x, int time) {
		this.x = x;
		this.time = time;
	}
	
	void timePass() {	//세포의 시간을 흐르게 만든다.
		if(!active && !isdead) {
			cnt++;
			
			if(cnt == x + 1) {
				active = true;
			}
		} else if(active) {
			isdead = true;
		}
	}
}
class XY {	//좌표 기록용 클래스, 큐에 사용
	public XY(int i2, int j2) {
		// TODO Auto-generated constructor stub
		i = i2;
		j = j2;
	}

	int i, j;
}

public class SWEA_5653_줄기세포배양 {
	
	static Queue<XY> q = new LinkedList<>();
	static int[] dirX = {-1, 1, 0, 0};
	static int[] dirY = {0, 0, -1, 1};
	static int N, M, K;
	static Cell[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st;
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			map = new Cell[N][M];
			
			for(int i = 0; i < N; i++) {
				//st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					//map[i][j] = new Cell(Integer.parseInt(st.nextToken()), 0);
					map[i][j] = new Cell(sc.nextInt(), 0);
					if(map[i][j].x != 0) {
						q.add(new XY(i, j));
					}
				}
			}
			
			System.out.println("#" + t + " " + sim());
		}
	}
	
	static int sim() {
		int time = 0, result = q.size();
		XY tmp;
		while(time < K) {
			tmp = q.peek();
			while(!q.isEmpty() && map[tmp.i][tmp.j].time == time) {	//시간대가 같을때만 시뮬레이션하기
				tmp = q.poll();
				if(map[tmp.i][tmp.j].active) {	//세포가 활성화 상태일때
					for(int a = 0; a < dirX.length; a++) {	//4방탐색
						int ii = tmp.i + dirX[a], jj = tmp.j + dirY[a]; 
						if(ii >= N || ii < 0 || jj >= M || jj < 0) continue;
						if(map[ii][jj].x < map[tmp.i][tmp.j].x && map[ii][jj].time == time + 1) {
							
							if(map[ii][jj].x == 0) result++;	//만약 빈공간이라면 증가, 다른 세포를 덮어씌운경우는 스킵
							
							map[ii][jj].x = map[tmp.i][tmp.j].x;
							map[ii][jj].time = time + 1;	//다음시간으로 넣기
							
							q.add(new XY(ii, jj));
						}
					}
				}
				else {
					//세포가 비활성화라면 시간을 보내고 기다려본다
					map[tmp.i][tmp.j].timePass();
					q.add(new XY(tmp.i, tmp.j));
				}
			}
			
			time++;
		}
		
		return result;
	}

}

 */