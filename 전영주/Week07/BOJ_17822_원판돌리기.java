package 전영주.Week07;

import java.io.*;
import java.util.*;

public class BOJ_17822_원판돌리기 {
    static int n,m,t;
    static int[][]arr;
    static int[][]rotateInput;
    static double avg;
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        t=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        rotateInput=new int[t][3];
        for (int i = 0; i < n; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
                
            }
        }
        for (int i = 0; i < t; i++) {
            st=new StringTokenizer(br.readLine());

            int x=Integer.parseInt(st.nextToken());//몇번째 원판
            int d=Integer.parseInt(st.nextToken());//시계0 반시계1
            int k=Integer.parseInt(st.nextToken());//k 칸
            if(d==0)k=n-k;
            print(arr);
            rotateSimul(x-1,k);
            print(arr);
            if(!eraseSimul()) {
            	//평균 구하기
            	makeValueAverage();
            }
            print(arr);
            
        }
        int answer=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer+=arr[i][j];
            }
        }
        System.out.println(answer);
    }
     static void print(int [][]v) {
            for (int i = 0; i < v.length; i++) {
                for (int j = 0; j < v[0].length; j++) {
                    System.out.print(v[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    //반시계만 고려
    private static void rotateSimul(int x,int k) {
        int temp=arr[x][0];
        for (int i = 0; i < n-k; i++) {
            arr[x][i]=arr[x][i+k];
        }
        
        for (int i = n-k; i < n-1; i++) {
			arr[x][i]=arr[x][i+n-k];//4
		}
         arr[x][n-k]=temp;
    }
    private static boolean eraseSimul() {
    	boolean flag=false;
    	avg=0;
        int [][]temp=new int[n][];
        for (int i = 0; i < n; i++) {
            temp[i]=Arrays.copyOf(arr[i], m);
        }
        //i,1
        for (int i = 0; i < n; i++) {
            
            if(temp[i][0]==temp[i][1]&&temp[i][0]!=0) {
                arr[i][0]=0;
                arr[i][1]=0;
                flag=true;
            }
            if(temp[i][0]==temp[i][m-1]&&temp[i][0]!=0) {
                arr[i][0]=0;
                arr[i][m-1]=0;
                flag=true;
            }
        }
        //같은 행 체크
        for (int i =0; i < n; i++) {
            for (int j = 1; j <m-1; j++) {
            	
                if(temp[i][j]==temp[i][j+1]&&temp[i][j]!=0) {
                arr[i][j]=0;
                arr[i][j+1]=0;
                flag=true;
                
                }
            }
        }
        
        //같은 열 체크
        for (int i =0; i < n-1; i++) {
            for (int j = 0; j <m; j++) {
                if(temp[i][j]==temp[i+1][j]&&temp[i][j]!=0) {
                arr[i][j]=0;
                arr[i+1][j]=0;
                flag=true;
                }
            }
        }
        if(!flag) {
        	sumArr();
        }
		return flag;
    }
    private static void sumArr() {
    	avg=0;
    	int count=0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				avg+=arr[i][j];
				if(arr[i][j]!=0)count++;
			}
		}
		avg/=count;
	}

    private static void makeValueAverage() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(arr[i][j]!=0) {
					if(arr[i][j]>avg)arr[i][j]--;
					else if(arr[i][j]<avg)arr[i][j]++;
				}
			}
		}
	}}
/*
 * 1초 512mb
 * n,m<50 
 * 한 원에서는 50개 즉 250개의 숫자가 최대
 * 인접한 애들만 보기 때문에 다 봐도 됨.
 * 1. 원판 돌리기
 * 2. k=0~n을 기준으로 양 옆 살펴보기
 * 만약 k가 n/2보다 크면 시계->n-k만큼 반시계
 * 
 * */