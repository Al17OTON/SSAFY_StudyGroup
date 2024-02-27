package 전영주.Week06;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {
	
	static int[] paperCount=new int[] {0,5,5,5,5,5};
	static int[][]arr=new int[10][10];
	static int answer=100;
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		recursive(0);
		//만약 -1이면 갱신이 안된것, 불가능한 예제
		System.out.println(answer==100?-1:answer);
	}
	private static void recursive(int count) {
		//backtracking
		if(answer<count)return;
		
		//다 붙였으면
		if(canPaperIn(0, 0, 10, 1)) {
			answer=Math.min(answer, count);
			return;
		}
		
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if(arr[r][c]==1) {
					for (int l = 5; l >=1 ; l--) {
						//종이가 있고, 넣을 수 있으면
						if(paperCount[l]>0&&canPaperIn(r, c, l,0)) {
							paperCount[l]--;
							stickOrRemove(r, c, l, 0);
							recursive(count+1);
							
							//원복
							paperCount[l]++;
							stickOrRemove(r, c, l, 1);
						}
					}
					return;
				}
			}
		}
		
		
	}
	private static void stickOrRemove(int i,int j,int len,int state) {
		for (int r = i; r < i+len; r++) {
			for (int c = j; c < j+len; c++) {
				arr[r][c]=state;
				
			}
		}
	}
	
	private static boolean canPaperIn(int i,int j,int len, int endCheck) {
		if(i+len>10||j+len>10)return false;

		for (int r = i; r < i+len; r++) {
			for (int c = j; c < j+len; c++) {
				if(arr[r][c]==endCheck)return false;
			}
		}
		return true;
	}
}
/*
 * 1초 512MB 
 * 10*10에 붙이기
 * 1이 적힌 칸은 모두 덮어야함
 * 다 붙여보기. 큰 애부터
 * 
 */