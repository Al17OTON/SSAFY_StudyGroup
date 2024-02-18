package 전영주.Week05;

import java.util.Scanner;

public class BOJ_15686_치킨배달 {

	static int hi = 0, ci = 0, M, N;
	static int[][] home, ch;
	static int[] sel;
	static int min = Integer.MAX_VALUE, answer = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) {
	    // TODO Auto-generated method stub
	    Scanner sc = new Scanner(System.in);
	    
	     N =sc.nextInt();
	    M = sc.nextInt();
	    
	    home = new int[2 * N][2];
	    ch = new int[14][2];
	    sel = new int[M];
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < N; j++) {
	            int a = sc.nextInt();
	            if(a == 1) {
	                home[hi][0]= i;
	                home[hi][1] = j;
	                hi++;
	            } else if(a == 2) {
	                ch[ci][0]= i;
	                ch[ci++][1] = j;
	            }
	        }
	    }
	    recursive(0, 0);
	    System.out.println(answer);
	}
	
	static void recursive(int idx, int n) {
	    if(idx == M) {
	        int sum = 0;
	        for(int i = 0; i < hi; i++) {
	        	 min = Integer.MAX_VALUE;
	            for(int j = 0; j < M; j++) {
	                min = Math.min(min ,getDis(i, sel[j])); 
	            }
	            sum += min;
	        }
	        answer = Math.min(answer, sum);
	        return;
	    }
	    if(n==ci)return;
	    sel[idx] = n;
	    recursive(idx+1, n + 1);
	    recursive(idx, n+1);
	}
	
	static int getDis(int i, int j) {
	    return Math.abs(home[i][0] - ch[j][0]) + Math.abs(home[i][1] - ch[j][1]);
	}
}