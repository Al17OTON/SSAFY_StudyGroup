package 전영주.Week06;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	static int answer=0;
	static int n;
	static boolean[][]visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		visited=new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				if(Integer.parseInt(st.nextToken())==1)visited[i][j]=true;
			}
		}
		
		dfs(0,1,0);
		System.out.println(answer);
	}
	private static void dfs(int x,int y,int state) {
		if(x==n-1&&y==n-1) {
			answer++;
			return;
		}
		int nX,nY;
		switch(state) {
		case 0://현재 가로인 경우
			//다음도 가로
			nX=x+0;
			nY=y+1;
			if(nX>=0&&nX<n&&nY>=0&&nY<n) {
				if(!visited[nX][nY]) {
					visited[nX][nY]=true;
					dfs(nX,nY,0);
					visited[nX][nY]=false;
				}
			}
			break;
		case 1://현재 대각선인경우
			//다음 가로
			nX=x+0;
			nY=y+1;
			if(nX>=0&&nX<n&&nY>=0&&nY<n) {
				if(!visited[nX][nY]) {
					visited[nX][nY]=true;
					dfs(nX,nY,0);
					visited[nX][nY]=false;
				}
			}
			//다음 세로
			nX=x+1;
			nY=y+0;
			if(nX>=0&&nX<n&&nY>=0&&nY<n) {
				if(!visited[nX][nY]) {
					visited[nX][nY]=true;
					dfs(nX,nY,2);
					visited[nX][nY]=false;
				}
			}
			break;
		case 2://현재 세로인 경우
			//다음도 세로
			nX=x+1;
			nY=y+0;
			if(nX>=0&&nX<n&&nY>=0&&nY<n) {
				if(!visited[nX][nY]) {
					visited[nX][nY]=true;
					dfs(nX,nY,2);
					visited[nX][nY]=false;
				}
			}
			break;
		}
		//대각선으로 가는 경우, 공통
		nX=x+1;
		nY=y+1;
		if(nX>=0&&nX<n&&nY>=0&&nY<n) {
			if(!visited[nX][nY]&&!visited[nX-1][nY]&&!visited[nX][nY-1]) {
				visited[nX][nY]=true;
				visited[nX-1][nY]=true;
				visited[nX][nY-1]=true;
				dfs(nX,nY,1);
				visited[nX][nY]=false;
				visited[nX-1][nY]=false;
				visited[nX][nY-1]=false;
			}
		}
	}	
}
/*
 * n<16 
 * bfs dfs
 * state 0 가로 1 대각선 2 세로
 * 가로 가로 0->0이동 : x+0 y+1
 * 가로 대각선 0->1이동 : x+1 y+1, x+1 y, x y+1
 * 세로 세로 2->2이동 : x+1 y+0
 * 세로 대각선 2->1이동 : x+0 y+1, x+1 y+0, x+1 y+1
 * 
 */