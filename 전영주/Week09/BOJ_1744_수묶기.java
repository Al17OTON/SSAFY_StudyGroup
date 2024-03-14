package problemSolving_homework.boj;
import java.util.*;
import java.io.*;
public class BOJ_1744_수묶기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int []arr=new int[n];
		for (int i = 0; i < n; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int stop=0;
		int temp=0;
		int answer=0;
		while(stop<arr.length&&arr[stop]<=0) {
			if(temp==0)temp=arr[stop];
			else {
				temp*=arr[stop];
				answer+=temp;
				temp=0;		
			}
			stop++;
		}
		answer+=temp;
		//음수 처리 완
		//1인 애들 처리
		while(stop<arr.length&&arr[stop]==1) {
			answer++;
			stop++;
		}
		int back=n-1;
		temp=0;
		while(stop<=back) {
			if(temp==0)temp=arr[back];
			else {
				temp*=arr[back];
				answer+=temp;
				temp=0;		
			}
			back--;
		}
		answer+=temp;
	
		
		System.out.println(answer);
	}

}
/*
 * 정렬 후 최대끼리..
 * 최소 끼리.
 * 만약 음수가 홀 수 개면 음수 중 제일 큰애만 혼자고 나머지는 묶기
 * 양수는 1이 아니면 묶기
 * 
 */