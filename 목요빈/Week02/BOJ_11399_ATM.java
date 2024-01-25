package 목요빈.Week02;

import java.util.Arrays;
import java.util.Scanner;

// BOJ S4 11399 : ATM
public class BOJ_11399_ATM {
	
	// N <= 1000
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] waiting = new int[N];
		int ans = 0;
		
		for(int i = 0; i<N; i++) {
			waiting[i] = sc.nextInt();
		}
		
		Arrays.sort(waiting);
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<=i; j++) {
				ans += waiting[j];
			}
		}
		
		System.out.println(ans);
		sc.close();
	}
}



