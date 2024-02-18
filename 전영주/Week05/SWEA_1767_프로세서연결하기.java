package 전영주.Week05;

import java.io.*;
import java.util.*;

public class SWEA_1767_프로세서연결하기 {
    static int n,maxCore,minLen;
    static int[][]arr;
    static boolean[][]visited;
    static List<Pos> cores=new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            n=Integer.parseInt(br.readLine());
            arr=new int[n][n];
            visited=new boolean[n][n];
            cores=new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st=new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                    //벽에 붙어있는 경우는 이미 연결됨->모든 경우에 항상 연결됨.
                    if(arr[i][j]==1) {
                    	visited[i][j]=true;
                        if(i==0||i==n-1||j==0||j==n-1)continue;
                        cores.add(new Pos(i,j));
                    }
                }
            } 
            maxCore=0;
            minLen=Integer.MAX_VALUE;
            dfs(0,0,0);
            System.out.println("#"+t+" "+minLen);
        }
       

    }
    private static void dfs(int index,int connectCore,int lineLen) {
        int dx[]= {1,0,-1,0};
        int dy[]= {0,1,0,-1};
        if(index==cores.size()) {
            //모든 코어를 살펴봣으니 갱신
        	//연결 최대,길이 최소
        	if(connectCore>maxCore) {
        		minLen=lineLen;
        		maxCore=connectCore;
        	}else if(connectCore==maxCore)minLen=Math.min(minLen, lineLen);
            return;
        }
        Pos p=cores.get(index);
        for (int i = 0; i < 4; i++) {
            int c=0;//벽과의 거리
            while(true) {
            	c++;
                int nX=p.x+c*dx[i];
                int nY=p.y+c*dy[i];
                //이미 다른 선이 지나갔거나 다른 코어가 있는 경우                
                if(visited[nX][nY]) {
                	c=0;
                	break;
                }
                if(nX==0||nX==n-1||nY==0||nY==n-1)break;//이 방향으로 연결 가능
            }
            int tmp=0;
            if(c>0) {//벽과 연결 가능하다
            	//전선 방문처리
            	while(++tmp<=c)visited[p.x+tmp*dx[i]][p.y+tmp*dy[i]]=true;
            	//다음 코어 dfs
	            dfs(index+1,connectCore+1,lineLen+c);
	            tmp=0;
	            while(++tmp<=c)visited[p.x+tmp*dx[i]][p.y+tmp*dy[i]]=false;
            }
            
        }
        //현재코어를 연결하지 않는 경우
        dfs(index+1,connectCore,lineLen);
    }
    static class Pos{
        int x;
        int y;
        public Pos(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    static void print(boolean [][]v) {
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
/*
 * n<12=> 재귀 부르트포스 모든 방향으로 연결하는 경우의 수
 * 4^12인데 
 * 상하좌우로 쭉 가는 경우
 * dfs 백트래킹
 * 
 * 60번째 방문 체크를 먼저 했어야했는데,, 경계선 체크를 먼저 해버렸다.
 * 원래 r<n이면 먼저해야하지만  n-1로 해줘서 무조건 범위 안이고 visited여부가 중요했다.
 */