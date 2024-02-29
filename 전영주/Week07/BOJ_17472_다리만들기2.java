package 전영주.Week07;

import java.util.*;
import java.io.*;
public class BOJ_17472_다리만들기2 {
	static int n,m,answer=Integer.MAX_VALUE;
	static int island=0;
	static int[][]arr;

	static int[][]adjMatrix;
	static int[]dx=new int[]{1,0,-1,0};
	static int[]dy=new int[]{0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][m];

        for (int i = 0; i < n; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //섬 구분하기
        int count=2;
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j]!=1)continue;
				distinguishIsland(i,j,count);
				count++;
			}
		}
        island=count-2;//섬 개수
        adjMatrix=new int[island+1][island+1];
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j]==0)continue;
				arr[i][j]-=1;
			}
		}
        //섬과 연결하는 다리 경우 구하기
        for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j]==0)continue;
				makeBridge(i, j, arr[i][j]);
			}
		}
        //프림
        prim(1);
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}
	private static void prim(int start) {
		int count=0;
		int result=0;
		PriorityQueue<Edge> pq=new PriorityQueue<>();
		boolean islandVisited[]=new boolean [island+2];
		islandVisited[start]=true;
		int curr=start;
		
		while(count<island-1) {			
			for (int i = 1; i < adjMatrix[curr].length; i++) {
				if(islandVisited[i])continue;
				if(adjMatrix[curr][i]>0)pq.add(new Edge(i, adjMatrix[curr][i]));
			}
			Edge next;
			while(true) {
				if(pq.isEmpty())return;
				next=pq.poll();
				if(!islandVisited[next.to])break;
			}
			islandVisited[next.to]=true;
			result+=next.weight;
			curr=next.to;
			count++;
		}
		answer=result;
	}
	private static void distinguishIsland(int startX,int startY,int index) {

		Queue<int[]>q=new ArrayDeque<>();
		q.add(new int[] {startX,startY});
		arr[startX][startY]=index;
		while(!q.isEmpty()) {
			int[] curr=q.poll();
			for (int i = 0; i < 4; i++) {
				int nX=curr[0]+dx[i];
				int nY=curr[1]+dy[i];
				if(nX<0||nX>=n||nY<0||nY>=m)continue;
				if(arr[nX][nY]!=1)continue;
				arr[nX][nY]=index;
				q.add(new int[] {nX,nY});
			}
		}
	}
	private static void makeBridge(int i,int j,int index) {
		
		for (int k = 0; k < 4; k++) {
			int count=1;
			while(true) {
				int nX=i+dx[k]*count;
				int nY=j+dy[k]*count;
				if(nX<0||nX>=n||nY<0||nY>=m)break;
				if(arr[nX][nY]==index)break;
				if(arr[nX][nY]>0) {//다른섬 만남
					if(count==2)break;
					int n=arr[nX][nY];
					int temp;
					if(adjMatrix[index][n]==0)temp=count-1;
					else temp=Math.min(adjMatrix[index][n], count-1);

					adjMatrix[index][n]=temp;
					adjMatrix[n][index]=temp;
					break;
				}
				count++;
			}
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static void print(int[][]arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println();
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
		}
	}
}
/*
 * 1초 512mb n,m<10  
 * 모든 섬을 연결하는 다리의 길이의 최솟값
 * 다리는 한 방향으로만 가로 or 세로
 * 1. bfs로 섬 구분하기
 * 2. dfs로 섬 연결하는 다리 만들기
 * -> 인접 리스트로 값 넣어주기
 * 하려했는덷 그냥 최소 거리만 저장하는게 나을듯
 * 
 * 3. 모든 섬을 연결하는 다리 길이의 최소값-> 최소 스패닝 트리
 * -> 크루스칼, 프림, 다익스트라 중 ,, 프림 선택?
 * 1초,10이고 다리가 직선이라 시간 괜찮을 듯
 * 
 * 다리 길이가 2 이상이어야함
 * 
 */