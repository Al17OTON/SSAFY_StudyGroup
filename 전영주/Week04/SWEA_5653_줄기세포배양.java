package Algoritm_homework;
import java.util.*;
import java.io.*;
public class 줄기세포배양 {
    static int[][] arr;// 입력 배열이자 bfs를 통한 파워값 저장 배열
    static int[][] visited;//방문여부 체크 배열이자 번식된 시간 저장 배열
    static int n,m,k,answer;
    static  int MAX;
    //static PriorityQueue<Cell> pq;
    static Queue<Cell> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int T=Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++)
        {
            st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            m=Integer.parseInt(st.nextToken());
            k=Integer.parseInt(st.nextToken());
            MAX=Math.max(n, m)+k*2+5;
            arr=new int [MAX][MAX];
            visited=new int[MAX][MAX];
            answer=0;
            //pq=new PriorityQueue<>();
            pq=new ArrayDeque<>();
            for (int i = MAX/2; i < n+MAX/2; i++) {
                st=new StringTokenizer(br.readLine());
                for (int j = MAX/2; j < m+MAX/2; j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                    //세포면 큐에 넣기
                    if(arr[i][j]>0) {
                    	//활성화 되는 시점을 큐에 넣기
                        pq.add(new Cell(arr[i][j],arr[i][j],i,j));
                         visited[i][j]=0;
                    }
                }
            }
            bfs();
          
            //활성 비활성이려면 현재 시간<power*2-1 + 도착시간이어야한다.
            for (int i =0; i < MAX; i++) {    
                for (int j = 0; j < MAX; j++) {
                    if(visited[i][j]<=k&&k<=visited[i][j]+arr[i][j]*2-1) {
                        answer++;
                    }
                }
            }
            System.out.println("#"+t+" "+answer);
        }
    }
    static void bfs() {
        
        int dx[]= {1,0,-1,0};
        int dy[]= {0,1,0,-1};
        
        while(!pq.isEmpty()) {
            Cell curr=pq.poll();
            if(curr.time+1>k)continue;
            for (int i = 0; i < 4; i++) {
                int nX=curr.x+dx[i];
                int nY=curr.y+dy[i];
              
                //방문한적 있는데 전에 방문한 시간이 현재 시간보다 작을 경우 넘어가
                //if(arr[nX][nY]!=0&&visited[nX][nY]<=curr.time+1)continue;
                if(arr[nX][nY]!=0&&visited[nX][nY]<curr.time+1)continue;
              
                //우선순위 큐가 아닐경우 넣어줘야하는 코드
                //시간이 같을 경우 생명력이 큰 경우가 입력됨
                if(visited[nX][nY]==curr.time+1&&curr.power<=arr[nX][nY])continue;
              
                visited[nX][nY]=curr.time+1;
                arr[nX][nY]=curr.power;
              
                //활성화 전인 시간대를 큐에 넣어주기 근데 k시간보다 넘어서면 굳이 넣어주지 말기
                if(curr.time+1<k)  pq.add(new Cell(curr.power,curr.time+1+curr.power,nX,nY));         
            }
        }
    }
    
    static class Cell implements Comparable<Cell>{
        int power;
        int time;
        int x;
        int y;
        public Cell(int power, int time, int x, int y) {
            super();
            this.power = power;
            this.time = time;

            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Cell c) {
            if(this.power<c.power) return 1;
            return -1;
        }   
    }
}
/*
 * 3초 n<50, m<50 배양시간 k<300 ->실제 그리드 크기는  600* 600로 하면 됨.
 *++ 인제님의 코멘토~ 1초 활성화 1초 비활성화 이므로 최대 150+50
 * 시간, 그래프 bfs?
 * 동시에 일경우 생명력 수치가 높은 애가
 * 배양을 K시간 시킨 후 배양 용기에 있는 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 개수
 * 결국 얼마나 퍼졌는지가 중요.
 * 죽더라도 소멸되는 건 아니고 ,,?
 * 활성화된 줄기 세포는 첫 1시간동안만 이동-> 한번만 이동
 * bfs Cell(power, time,x,y)
 *  큐를 power 으로 우선순위 큐, visited에 시간 저장하기. 0 접근 안한 노드, 1 초기상태
 *  개수는? 일일히 셀까? counting을 할까- 카운팅으로
 * 언제 퍼질지. counting으로 큐에 넣어주면서
 * 
 * 오래걸린 이유:
 * 1.헐.. 살아있는 줄기세포의 개수만 출력해야함,,
 * 어떤 세포가 저장됬는지 배열 만들어서 저장
 * 2, m값이랑 k값이랑 헷갈림 ㅜ
 * 3. x시간동안 비활성이고  x시간동안 살아있음!!쉣 난 x+1인줄
 * 50개의 테스트케이스 중 44개만 맞음
 * -> 이유: Tlqkf 욕이 나오는데,,  맨처음 visited를 0으로 했다가 방문처리가 안되 1로 바꿔 진행했는데, 이게 문제였음..
 */
