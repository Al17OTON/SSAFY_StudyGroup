package 전영주.Week08;

import java.io.*;
import java.util.*;

public class SWEA_7208_지도칠하기 {
    static int n,answer;
    static boolean[][]adjMatrix;
    static int[][]basicColor;
    static int[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new  BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for (int t = 1;  t<= T; t++) {
            n=Integer.parseInt(br.readLine());
            adjMatrix=new boolean[n][n];
            basicColor=new int[n][4];
            visited=new int[n];
            answer=4;
            StringTokenizer st=new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                basicColor[i][0]=Integer.parseInt(st.nextToken());
                int temp=basicColor[i][0];
                if(temp==4)temp=1;
                for (int j = 1; j <= 3; j++) {
                    basicColor[i][j]=temp;
                    temp++;
                    if(temp==4)temp=1;
                }
            }
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine());
               
                for (int j = 0; j < n; j++) {
                    int temp=Integer.parseInt(st.nextToken());
                    if(temp==1)adjMatrix[i][j]=true;
                }
            }        
            dfs(0,new int[n]);
        }
    }

    private static void dfs(int index,int[]sel) {
        if(index==n) {
            //가능한 조합임.
            int count=0;
            for (int i = 0; i < sel.length; i++) {
                if(sel[i]!=basicColor[i][0])count++;
            }
            answer=Math.min(answer, count);
        }
       
        for (int i = 0; i <4; i++) {
        	sel[index]=basicColor[index][i];
        	boolean flag=true;
            for (int next=0;next<index;next++) {
                if(sel[next]==sel[index]) {//바꿔야한다ㅏ.
                    flag=false;
                    break;
                }
            }
            if(flag) {
            	dfs(index+1,sel);
            }
            
        }
        
        
    }

}
/*
 * n<8 최소 변경 횟수
 * 인접한 나라의 색을 인덱스로 하는 배열에 ++
 * 만약 1을 원하는 색으로 하면, 1과 인접한 나라들 돌면서 색 인덱스++
 * 나라 2도 돌면서 만약 자기와 인접한 나라에 지정된 색이 있으면 다른 색으로 
 * n이 작아서 조합 완탐도 가능
 * but dp가 정배인거로 알고 있는데
 * 이건 칠해야하는 기본 색이 있음 -> 재귀 백트래킹 완탐으로 의도한 문제 인듯
 * 1부터 얠 바꾸는 경우, 아닌 경우 여러가지.. 해보기
 * visited에 색 넣어놓기
 */