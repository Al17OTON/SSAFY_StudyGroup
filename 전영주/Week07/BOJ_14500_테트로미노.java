package 전영주.Week07;

import java.io.*;
import java.util.*;

public class BOJ_14500_테트로미노 {
    static int n,m,answer=0;
    static int[][]arr;
    static boolean[][] visited;
    static int[]dx= {1,0,-1,0};
    static int[]dy= {0,1,0,-1};
    static int[][] rotationDx=new int[][] {{0,0,1},{1,2,1},{0,0,-1},{1,2,1}};
    static int[][] rotationDy=new int[][] {{1,2,1},{0,0,1},{1,2,1},{0,0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        visited=new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
                
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
            	dfs(i,j,0,0);
            	answer=Math.max(answer,getMaxSumㅜ(i,j));
            }
        }
		System.out.println(answer);
    }
    private static void dfs(int i, int j, int depth,int sum) {
    	if(depth==4) {
    		answer=Math.max(answer, sum);
    		return;
    	}
    	for (int k = 0; k < 3; k++) {
    		int nX=i+dx[k];
    		int nY=j+dy[k];
    		if(nX<0||nX>=n||nY<0||nY>=m)continue;
    		if(visited[nX][nY])continue;
    		visited[nX][nY]=true;
    		dfs(nX,nY,depth+1,sum+arr[nX][nY]);
    		visited[nX][nY]=false;
		}
		
	}
    //ㅜ의 회전, 대칭 경우 다 해보기
    private static int getMaxSumㅜ(int i,int j) {
    	int sum=0;
		for (int rotatateIndex = 0; rotatateIndex < rotationDx.length; rotatateIndex++) {
			sum=Math.max(sum,getSum(i,j,arr[i][j],rotatateIndex));
		}
    	
    	return sum;
	}
	private static int getSum(int i, int j,int sum,int rotatateIndex) {
		
		for (int k = 0; k < 3; k++) {
	    		int nX=i+rotationDx[rotatateIndex][k];
	    		int nY=j+rotationDy[rotatateIndex][k];
	    		if(nX<0||nX>=n||nY<0||nY>=m)return 0;
				sum+=arr[nX][nY];
			}
		return sum;
    }

}
/*
 * 2s 512mb
 * 좌표 선택, 도형 선택, 방향 선택
 * 하늘 가로: 2개
 * 노랑 네모: 1개
 * 주황 ㄴ: 8개
 * 초록 ㄹ: 8개
 * 분홍 ㅜ: 4개 
 * 2초롸 완탐? 500*500*5*8
 * ㄴ에서 x,y 기준 1 0, 0 1, -1 0, 0 -1 
 * 				
 * dx,dy를 다 적고 있었는데 권이에게 조언을 얻음.
 * dfs로 깊이가 4만큼 다 가보기
 * 
 */
//static int[][][] rotationDx=new int[][][] 
//    	{{{0,0,0},{1,2,3}},
//    	{{1,0,1}},
//    	{{1,2,2},{0,0,-1},{-1,-2,-2},{-1,-1,-1},{0,-1,-2},{1,1,1},{-1,-2,-2},{0,0,1}},
//    	{},
//    	{}};
//    static int[][][]rotationDy=new int[][][] 
//    	{{{1,2,3},{0,0,0}},
//    	{{0,1,1}},
//    	{{0,0,1},{1,2,2},{0,0,-1},{0,1,2},{1,1,1},{0,1,2},{0,0,1},{1,2,2}},
//    	{},
//    	{}};