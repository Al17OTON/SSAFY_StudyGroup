package problemSolving_homework.boj;

import java.util.*;
import java.io.*;
public class BOJ_17143_낚시왕 {
	static int r,c,answer;
	static int haveToEat;
	static boolean[][]map;
	static PriorityQueue<Shark> pq;
	static int[]dx=new int[] {-1,1,0,0};
	static int[]dy=new int[] {0,0,1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		haveToEat=r;
		
		pq=new PriorityQueue<>();
		int m=Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < m; i++) {
			st=new StringTokenizer(br.readLine());			
			int rr=Integer.parseInt(st.nextToken())-1;
			int cc=Integer.parseInt(st.nextToken())-1;
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			pq.add(new Shark(rr, cc, s, d, z));
			if(cc==0&&haveToEat>rr)haveToEat=rr;
		}
		int currIndex=0;
		while(currIndex<c) {
			simulation(currIndex);
			currIndex++;
		}
		System.out.println(answer);
	}
	private static void simulation(int currIndex) {
		//사람 이동
		//낚시
		//큐 돌면서 haveToEat인 애는 죽이기
		//상어 이동
		int nextEat=r;
		map=new boolean[r][c];
		PriorityQueue<Shark> temp=new PriorityQueue<>();
		while(!pq.isEmpty()) {
			Shark curr=pq.poll();
			if(curr.x==haveToEat&&curr.y==currIndex)answer+=curr.size;
			else {
				int step=1;
				int nX=curr.x,nY=curr.y;
				int factor=1;
				while(step<=curr.speed) {
					if(nX+dx[curr.dir]*factor<0||nX+dx[curr.dir]*factor>r-1||nY+dy[curr.dir]*factor<0||nY+dy[curr.dir]*factor>c-1)factor*=-1;
					nX+=dx[curr.dir]*factor;
					nY+=dy[curr.dir]*factor;
					step++;	
				}
				if(map[nX][nY])continue;
//				System.out.println(curr.x+" "+curr.y+" "+nX+" "+nY);

				if(factor==-1) {
					if(curr.dir%2==0) curr.dir++;
					else curr.dir--;
				}
				map[nX][nY]=true;
				temp.add(new Shark(nX, nY,curr.speed,curr.dir,curr.size));
				//다음에 먹을 애인지 확인하기
				if(nY==currIndex+1&&nX<nextEat) {
					nextEat=nX;
				}
			}
		}
		haveToEat=nextEat;
		pq.addAll(temp);
	}
	
	static class Shark implements Comparable<Shark>{
		int x,y;
		int speed;
		int dir;
		int size;
		public Shark(int x, int y, int speed, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		@Override
		public int compareTo(Shark o) {
			return Integer.compare(o.size, this.size);
		}
	}
}
/*
 * 1초 512mb r,c<100
 * 낚시왕은 오른쪽 으로 이동하면서 그 열에서 x값이 제일 작은 상어를 잡음
 * 그 후 상어 이동
 * 격자 넘어가면 반대로 이동 
 * 이동 후 한칸에 여러마리가 있으면 가장 큰 상어만 남음, 같은 크기를 갖는 상어는 엇음
 * 구현.
 * 상어가 엄청 많으면 겹치는 애도 많을텐데 이걸 줄일 수 있는 방법이 없을까
 * 우선순위 큐? 근데 결국 다 돌아야함.
 * 도착할 좌표에 우선순위큐? 그럼 r*c 개수의 큐가 있어야함.
 */