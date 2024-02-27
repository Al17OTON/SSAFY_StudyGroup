package 전영주.Week07;

import java.io.*;
import java.util.*;

public class BOJ_17281_야구 {
    static int N,answer;
    static int[]producerPick;
    static int[][]battingResult;
    static int batCount;
    static boolean[]visited=new boolean[8];
    public static void main(String[] args) throws Exception {
        BufferedReader br=new  BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        battingResult=new int[N][8];
        producerPick=new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            producerPick[i]=Integer.parseInt(st.nextToken());            
            for (int j = 0; j < 8; j++) {
                battingResult[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        permute(0,new int[8]);
        System.out.println(answer);
    }
    //선수 순열 구하기
    private static void permute(int count,int[]order) {
        //다 선택했으면
        if(8==count) {
            int sum=0;
            int ening=0;
            batCount=0;
            while(ening<N) {
                sum+=getScore(order,ening);
                ening++;
            }
            answer=Math.max(sum,answer);
            return;
        }
        for (int i = 0; i < 8; i++) {
            if(visited[i])continue;
            visited[i]=true;
            order[count]=i;
            permute(count+1,order);
            visited[i]=false;
        }
    }
    private static int getScore(int[]order,int ening) {
        int result=0;
        int outCount=0;
        
        boolean ru[]=new boolean[4];
        //3아웃이 나올때 까지
        while(outCount<3) {
            
            int curr=0;
            //4번 타자는 1번 선수로 픽스되어있다.
            if(batCount==3) {
                curr=producerPick[ening];            
            }else if(batCount<3){
                curr=battingResult[ening][order[batCount]];
            }else if(batCount>3){
                curr=battingResult[ening][order[batCount-1]];
            }
            // 타자의 결과가 아웃, 홈런이 아닌, 홈런 일때로 if else
            if(curr==0) {//아웃
                outCount++;
            }else if(curr!=4){//홈런이 아닌 
                for (int i = 3; i>=1;i--) {
                    if(ru[i]) {
                        if(i+curr>=4) {
                            result++;
                            ru[i]=false;
                        }else {
                            ru[i+curr]=true;
                            ru[i]=false;
                        }
                    }
                }
                ru[curr]=true;
            }else {//홈런
                result++;
                for (int i = 1; i <=3; i++) {
                    if(ru[i])result++;
                }
                ru=new boolean[4];
            }
            batCount++;
            if(batCount>8)batCount=0;    
        }

        return result;
    }
}
/*
 * 1초 n<50
 * 1번 선수는 무조건 4번 타자.
 * 무조건 큰게 좋지 않음. 안타와 홈런이 적절히 잇어야함. 
 * 어떤게 최적인지 모르겠음. 순열로 완탐해야하나봐ㅜ
 * 그냥 구현함
 * */
