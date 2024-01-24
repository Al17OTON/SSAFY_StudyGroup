package Week02;

import java.util.Arrays;
import java.util.Scanner;


public class BOJ_11399_ATM {
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		sum += arr[0];
		
		for(int i = 1; i < N; i++) {
			arr[i] += arr[i - 1];
			sum += arr[i];
		}
		System.out.println(sum);
	}
}
