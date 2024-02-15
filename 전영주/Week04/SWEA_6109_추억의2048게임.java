package 전영주.Week04;

import java.io.*;
import java.util.*;

public class SWEA_6109_추억의2048게임 {
    static int [][]arr;
    static int n;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st=new StringTokenizer(br.readLine());
            n=Integer.parseInt(st.nextToken());
            String direction=st.nextToken().toString();
            arr=new int[n][n];
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            int start,end;
            switch(direction) {
            case "left":
                for (int i = 0; i < n; i++) {
                	Queue<Integer> q=new ArrayDeque<Integer>();
                    for (int j = 0; j <n; j++) {
                    	//값이 있으면 큐에 넣어주기
                    	if(arr[i][j]>0) q.offer(arr[i][j]);
                    	//값 없애기
                    	arr[i][j]=0;
                    }
                    int index=0;
                    //큐가 빌때까지 값 합쳐주기
                    while(!q.isEmpty()) {
                    	int prev=q.poll();
                    	//하나 뺐을 때 다음 값이 없을 수 있느니 확인하기. 없으면 바로 넣어주기
                    	if(q.isEmpty()) {
                    		arr[i][index]=prev;
                    		break;
                    	}
	                    if(prev==q.peek()) {
	                    	arr[i][index]=prev*2;
	                    	q.poll();
	                    	
	                    }else {
	                    	arr[i][index]=prev;
	                    }
	                    index++;
                    }
                    
                    
                }
                break;
                
            case "right":
            	for (int i = 0; i < n; i++) {
                	Queue<Integer> q=new ArrayDeque<Integer>();
                    for (int j = n-1; j >=0; j--) {
                    	//값이 있으면 큐에 넣어주기
                    	if(arr[i][j]>0) q.offer(arr[i][j]);
                    	//값 없애기
                    	arr[i][j]=0;
                    }
                    int index=n-1;
                    //큐가 빌때까지 값 합쳐주기
                    while(!q.isEmpty()) {
                    	int prev=q.poll();
                    	if(q.isEmpty()) {
                    		arr[i][index]=prev;
                    		break;
                    	}
	                    if(prev==q.peek()) {
	                    	arr[i][index]=prev*2;
	                    	q.poll();
	                    	
	                    }else {
	                    	arr[i][index]=prev;
	                    }
	                    index--;
                    }
                    
                    
                }
                break;
                
            case "up":
            	for (int j = 0; j < n; j++) {
                	Queue<Integer> q=new ArrayDeque<Integer>();
                    for (int i = 0; i <n; i++) {
                    	//값이 있으면 큐에 넣어주기
                    	if(arr[i][j]>0) q.offer(arr[i][j]);
                    	//값 없애기
                    	arr[i][j]=0;
                    }
                    int index=0;
                    //큐가 빌때까지 값 합쳐주기
                    while(!q.isEmpty()) {
                    	int prev=q.poll();
                    	if(q.isEmpty()) {
                    		arr[index][j]=prev;
                    		break;
                    	}
	                    if(prev==q.peek()) {
	                    	arr[index][j]=prev*2;
	                    	q.poll();
	                    	
	                    }else {
	                    	arr[index][j]=prev;
	                    }
	                    index++;
                    }
                    
                    
                }
                break;
            case "down":
            	for (int j = 0; j < n; j++) {
                	Queue<Integer> q=new ArrayDeque<Integer>();
                    for (int i = n-1; i >=0; i--) {
                    	//값이 있으면 큐에 넣어주기
                    	if(arr[i][j]>0) q.offer(arr[i][j]);
                    	//값 없애기
                    	arr[i][j]=0;
                    }
                    int index=n-1;
                    //큐가 빌때까지 값 합쳐주기
                    while(!q.isEmpty()) {
                    	int prev=q.poll();
                    	if(q.isEmpty()) {
                    		arr[index][j]=prev;
                    		break;
                    	}
	                    if(prev==q.peek()) {
	                    	arr[index][j]=prev*2;
	                    	q.poll();
	                    	
	                    }else {
	                    	arr[index][j]=prev;
	                    }
	                    index--;
                    }
                }
                break;
            }
            System.out.println("#"+test_case);
            for (int i = 0; i < n; i++) {
            	for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
            
        }
    }
}
/*
 * n*n <=20
 * 한번 실행 후 결과이므로 부르트포스로
 * 고려할 부분: 벽에서부터 보기, 같을 경우 합쳐서 앞에꺼랑 합치고 그 빈 인덱스 기억하기
 * left y 0~n-1
 * right y n-1~0
 * up x 0~n-1
 * down x n-1~0
 */
