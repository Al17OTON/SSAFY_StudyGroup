package 목요빈.Week02;

import java.util.Scanner;

// BOJ B2 2798 : 블랙잭
public class BOJ_2798_블랙잭 {

	// N <= 100
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] cards = new int[N];
		int ans = 0;
		
		for(int i = 0; i<N; i++) {
			cards[i] = sc.nextInt();
		}
		
		// 3중 for문 -> 브루트 포스
		for(int i = 0; i<N-2; i++) {
			for(int j = i+1; j<N-1; j++) {
				for(int k = j+1; k<N; k++) {
					int temp = cards[i] + cards[j] + cards[k];
					if(temp <= M) {
						ans = Math.max(temp,  ans);
					}
				}
			}
		}
		
		System.out.println(ans);
		sc.close();
	}
}
