package 전영주.Week06;
import java.io.*;
import java.util.*;

public class BOJ_16987_계란으로계란치기 {
    static int n,answer=0;
    static int[][]eggs;//내구도, 무게
    
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        eggs=new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            eggs[i][0]=Integer.parseInt(st.nextToken());//내구도
            eggs[i][1]=Integer.parseInt(st.nextToken());//무게
        }
        breakEgg(0);
        System.out.println(answer);
    }
    private static void breakEgg(int hIndex) {
    	
        if(hIndex>=n) {
        	int temp=0;
	        for (int i = 0; i < n; i++) {
				if(eggs[i][0]<=0)temp++;
			}
	        answer=Math.max(answer, temp);
        	return;
        }
        if(eggs[hIndex][0]<=0) {
        	breakEgg(hIndex+1);//다음 계란 잡기
        	return;
        }
        boolean flag=false;
        for (int i = 0; i < n; i++) {//i=hIndex+1이 왜 안돼??
			
			if(hIndex==i)continue;//잡은 계란 깨는 경우
			if(eggs[i][0]<=0)continue;//깰려는 계란 깨져있음

			eggs[i][0]-=eggs[hIndex][1];
			eggs[hIndex][0]-=eggs[i][1];
			flag=true;
			breakEgg(hIndex+1);
			eggs[i][0]+=eggs[hIndex][1];
			eggs[hIndex][0]+=eggs[i][1];
		}
        //더이상 칠 계란이 없다. 
        if(flag==false)breakEgg(n);
    }
}
/*
 * 최대 개수
 * 뭐로 뭘 먼저 깰지? 순서가 중요하나. 조합이 정해지면 순서는 상관ㄴㄴ 깰수 있느냐 없느냐만 중요
 * 손에 든걸 깰 때까지 임의로 다른 계란 깨고
 * 손에 든걸 깨야 다음 오른쪽 계란
 * 손에 있는 것보다 무게가 낮은애를 쳐...흠
 * 
 * 순서대로 쳐야함. 정렬 ㄴㄴ=>그리디 ㄴㄴ 
 * 백트래킹 재귀 완탐 dfs 최악 n! 
 * 
 * 헤멘 포인트 :  칠 애가 있으면 무조건 쳐야한다!
 */