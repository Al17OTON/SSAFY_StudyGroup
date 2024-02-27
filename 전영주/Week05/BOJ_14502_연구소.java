package 전영주.Week05;

import java.io.*;
import java.util.*;

public class BOJ_14502_연구소 {
	static int n,m,answer=0;
	static int [][]arr;
	static boolean [][]visited;
	static List<Pos> virus;
	static int[]dx=new int[] {1,0,-1,0};
	static int[]dy=new int[] {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		arr=new int[n][m];
		virus=new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(arr[i][j]==2)virus.add(new Pos(i,j));
			}
		}
		dfs(0,0);
		System.out.println(answer);
	}
	
	private static void dfs(int count,int r) {
		if(count==3) {
			int[][]temp=new int[n][m];
			for (int i = 0; i < n; i++) {
				temp[i]=Arrays.copyOf(arr[i], m);
			}
			bfs(temp);
			return;
		}
		for (int i = r; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j]==0) {
					arr[i][j]=1;
					dfs(count+1,i);
					arr[i][j]=0;			
				}
			}
		}
	}
	private static void bfs(int[][]_arr) {
		visited=new boolean[n][m];
		Queue <Pos> q=new ArrayDeque<>();
		for(Pos p:virus) {
			q.offer(p);
			visited[p.x][p.y]=true;
		}
		while(!q.isEmpty()) {
			Pos curr=q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX=curr.x+dx[i];
				int nextY=curr.y+dy[i];
				if(nextX<0||nextX>=n||nextY<0||nextY>=m)continue;
				if(_arr[nextX][nextY]!=0)continue;
				if(visited[nextX][nextY])continue;
				_arr[nextX][nextY]=2;
				visited[nextX][nextY]=true;
				q.offer(new Pos(nextX,nextY));
			}
		}
		int count=0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(_arr[i][j]==0) {
					count++;			
				}
			}
		}
		answer=Math.max(answer, count);
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
}
/*
 * n,m<8 -> dfs 완탐 가능
 * 64*63*62 벽 경우의 수.
 * 안전 영역의 최댓값.
 * 벽 조합 dfs 그에 따른 bfs 실행 후 결과 값 for문으로 카운팅
 */