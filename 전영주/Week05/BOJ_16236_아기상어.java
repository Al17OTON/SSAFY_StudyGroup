package 전영주.Week05;

import java.util.*;
import java.io.*;

public class BOJ_16236_아기상어 {
    static int n,answer=0;
    static int[][]arr;
    static boolean [][][][]visited;//x,y,size,eatcount
    static Pos shark;
    static int numCount[];
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];
        visited=new boolean[n][n][30][30];
        numCount=new int [11];//상어 무게가i일때 먹을 수 있는 물고기 개수
        for (int i = 0; i < n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
               int temp=Integer.parseInt(st.nextToken());
                if(temp==9){
                    shark=new Pos(i,j,0,2,0,0);
                    arr[i][j]=0;
                }
                else if(temp>0) {
                   arr[i][j]=temp;             
                   for (int j2 = 10; j2 > temp; j2--) {
                      numCount[j2]++;
                   }
                }
            }
        }
        visited[shark.x][shark.y][2][0]=true;
        //dfs(shark);
        bfs();
        System.out.println(answer);
    }
    private static void bfs() {
       int dx[]= {-1,0,0,1};
        int dy[]= {0,-1,1,0};
        
        PriorityQueue<Pos> q=new PriorityQueue<>();
        q.offer(shark);
        while(!q.isEmpty()) {
           Pos curr=q.poll();
            if(arr[curr.x][curr.y]>0&&arr[curr.x][curr.y]<curr.sharkSize){
                arr[curr.x][curr.y]=0;  
                //System.out.println(curr.x+" "+curr.y+" shark: "+curr.sharkSize+" time: "+curr.time);
                q.clear();
                if(curr.eatCount==curr.sharkSize) {
                    visited[curr.x][curr.y][curr.sharkSize+1][0]=true;
                    curr.sharkSize++;
                    curr.eatCount=0;
                }
                else {
                    visited[curr.x][curr.y][curr.sharkSize][curr.eatCount]=true;
                }
                answer=curr.time;
            }
           //물고기 다 먹었는지 판별 코드 필요.
//            if(numCount[curr.sharkSize]-curr.eatSum<=0) {
//               answer=curr.time;
//               break;
//            }
            
           for (int i = 0; i < 4; i++) {
                int nX=curr.x+dx[i];
                int nY=curr.y+dy[i];
         
                //지나갈 수 있는지
                if(nX<0||nX>=n||nY<0||nY>=n)continue;
                if(visited[nX][nY][curr.sharkSize][curr.eatCount])continue;
                if(arr[nX][nY]>curr.sharkSize)continue;
                //크기가 같거나 물고기가 없는 경우
                if(arr[nX][nY]==curr.sharkSize||arr[nX][nY]==0) {
                   visited[nX][nY][curr.sharkSize][curr.eatCount]=true;
                   q.offer(new Pos(nX,nY,curr.time+1,curr.sharkSize,curr.eatCount,curr.eatSum));
                }
                else {           
                    //먹는다.
                    q.offer(new Pos(nX,nY,curr.time+1,curr.sharkSize,curr.eatCount+1,curr.eatSum+1));

                    break;
                }
            }
        }
   }

    static class Pos implements Comparable{ 
        int x;
        int y;
        int time;
        int sharkSize;
        int eatCount;
        int eatSum;
        public Pos(int x, int y, int time, int sharkSize,int eatCount,int eatSum) {
            super();
            this.x = x;
            this.y = y;
            this.time = time;
            this.sharkSize = sharkSize;
            this.eatCount=eatCount;
            this.eatSum=eatSum;
        }
        @Override
        public int compareTo(Object o) {
            if(o instanceof Pos){
                Pos p=(Pos)o;
                if(this.time>p.time)return 1;
                else if(this.time==p.time){
                    if(this.x>p.x)return 1;
                    if(this.x==p.x&&this.y>p.y)return 1;
                }    
            }
            return -1;
        }
        
    }
}
/*
 * N*N <20 
 * 초기 상어 크기 2, 엄마상어 도움 요청하기 전까지 시간
 * 1.curr.size<fish 면 지나갈 수 없음
 * 2.먹을 수 있는 물고기가 여러마리면 상-좌-우 하 좌-우 최소거리순
 * dfs 구현,왜 최대 최소 말이 없지. 왜냐 
 * 지나갔다가 또 지나갈 수도 있는데,, 그럼 크기에 따른 방문 배열을 해야하나?좋은데
 *먹을 수 없으몀 바로 엄마 부르는데 이게 언제일까.
 * 갯수로 따지기에는 있지만 못가는 경우가 있다
 * 그냥 bfs 빠져나올때로 하자.
 * 그냥 구현인가?
 * 큐에 넣으면서 레벨에 따라 ..큐 비워주기->최소 경우의 수를 구하는게 아닌 거리에 따른 정해진 루트를 가야하므로
 * 우선순위: 현재 시간 오름차순(레벨을 따지기 위해==이동거리 오름차순)-> x좌표 오름차순(위방향 우선)->y좌표 오름차순(왼쪽 우선)
 */