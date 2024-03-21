package problemSolving_homework.boj;

import java.util.*;
import java.io.*;

public class BOJ_3197_백조의호수 {
	static int R,C,answer=0;
	static boolean flag;
	static char[][]arr;
	static int[][]person;
	static boolean [][]waterVisited;
	static boolean [][]personVisited;
	static Queue<int[]>waterQ=new ArrayDeque<>();
	static Queue<int[]> personQ=new ArrayDeque<>();

	static int[]dx=new int[] {1,0,-1,0};
	static int[]dy=new int[] {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		arr=new char[R][C];
		person=new int[2][2];
		waterVisited=new boolean[R][C];
		personVisited=new boolean[R][C];
		personQ=new ArrayDeque<int[]>();
		waterQ=new ArrayDeque<int[]>();
		int p=0;
		for (int i = 0; i < R; i++) {
			String temp=br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j]=temp.charAt(j);
				if(arr[i][j]=='L') {
					person[p]=new int[] {i,j};
					p++;
					waterQ.offer(new int[] {i,j});
					
				}
				else if(arr[i][j]=='.') {
					waterVisited[i][j]=true;
					waterQ.offer(new int[] {i,j});
				}
				
			}
		}
		flag=true;
		//빙하 녹이기 bfs
		//visited=new boolean[R][C];
		
		personQ.offer(new int[] {person[0][0],person[0][1]});
		personVisited[person[0][0]][person[0][1]]=true;
		while(flag) {
			personBFS();
			meltBFS();
			if(!flag)break;
			
			answer++;
		}
		System.out.println(answer);
	}
	
	private static void meltBFS() {
		int size=waterQ.size();
		while(size-->0) {
			int []curr=waterQ.poll();
			for (int i = 0; i < 4; i++) {
				int nextX=curr[0]+dx[i];
				int nextY=curr[1]+dy[i];
				if(nextX<0||nextX>=R||nextY<0||nextY>=C)continue;
				if(waterVisited[nextX][nextY])continue;
				waterVisited[nextX][nextY]=true;
				waterQ.offer(new int[] {nextX,nextY});
			}
		}
		
	}
	private static void personBFS() {
		Queue<int[]> nextQ=new ArrayDeque<int[]>();
		while(!personQ.isEmpty()) {
			int []curr=personQ.poll();
			for (int i = 0; i < 4; i++) {
				int nextX=curr[0]+dx[i];
				int nextY=curr[1]+dy[i];
				if(nextX<0||nextX>=R||nextY<0||nextY>=C)continue;
				if(personVisited[nextX][nextY])continue;
				if(nextX==person[1][0]&&nextY==person[1][1]) {
					//만났다.
					flag=false;
					return;
				}
				personVisited[nextX][nextY]=true;
				if(waterVisited[nextX][nextY]) {
					//물이므로 현재 갈 수 있음
					personQ.offer(new int[] {nextX,nextY});
					
				}else {
					//얼음. 다음에 갈 수 있음
					nextQ.offer(new int[] {nextX,nextY});
				}
			}
		}
		personQ=nextQ;
	}
	
}
	/*
	 * 일반 땅과 접촉한 모든 빙하 공간이 녹는다=> 일반 땅으로 바뀐다.
	 * 빙하가 녹는건  bfs
	 * 나는 두 사람 다 움직인다고 생각을 했는데, 
	 * 움직인 거리가 중요하지 않은 이상 한 사람은 고정하고 다른 한 사람만 움직인다고 생각
	 * 
	 * */
