package 전영주.Week08;
import java.util.*;
import java.io.*;
public class SWEA_4013_특이한자석 {
	static int answer;
	static String[]magnet;
	static int[]firstIndex;
	static int[]nextIndex;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		int k=Integer.parseInt(br.readLine());
		magnet=new String[4];
		firstIndex=new int[4];
		nextIndex=new int[4];
		answer=0;
		for (int i = 0; i < 4; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			StringBuilder sb=new StringBuilder();
			for (int j = 0; j< 8; j++) {
				sb.append(st.nextToken().toString());
			}
			magnet[0]=sb.toString();
		}
		for (int i = 0; i < k; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken())-1;
			int dir=Integer.parseInt(st.nextToken());//1은 시계 
			rotate(num,num+1,dir);
			rotate(num,num-1,dir);
			firstIndex=Arrays.copyOf(nextIndex, 4);
			getScore();
		}
	}
	private static void getScore() {
		for (int i = 0; i < 4; i++) {
			if(magnet[i].charAt(firstIndex[i])==1)answer+=Math.pow(2, 0);
		}
	}
	private static void rotate(int curr,int other,int dir) {
		if(other<0||other>=4)return;
		//0번 자석의 2번째 랑 1번의 6번째랑
		//1번 자석의 2번째랑 2번의 6번째랑
		int c,o;
		if(curr<other) {
			c=magnet[curr].charAt((firstIndex[curr]+2)%4);
			o=magnet[other].charAt((firstIndex[other]+6)%4);
			if(c==o) {
				rotate(other,other+1,dir*-1);
				nextIndex[other]=(firstIndex[other]+dir)%4;
			}
		}else {
			c=magnet[curr].charAt((firstIndex[curr]+6)%4);
			o=magnet[other].charAt((firstIndex[other]+2)%4);
			if(c==o) {
				rotate(other,other-1,dir*-1);
				nextIndex[other]=(firstIndex[other]+dir)%4;
			}
		}

	}
}
/*
 * 3초 자석 4개 날 8개
 * 4개의 자석 8개의 날
 * 서로 붙어있는 날의 자성과 다를 경우에만 반대방향으로 회전.
 * 회전하려고 할때 현재 자석과 비교해야함
 * 회전 시 각 연결부의 반대 여부를 알아야한다.
 * 
 */