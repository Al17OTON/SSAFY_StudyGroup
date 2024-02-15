package 목요빈.Week02;

import java.util.*;

// BOJ B2 8320 : 직사각형을만드는방법
public class BOJ_8320_직사각형을만드는방법 {

	// n <= 10000
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans = 0;

		for(int i = 1; i<=Math.round(n/2.0); i++) { // 가로 길이
			for(int j = i; i*j <= n; j++) { // 세로 길이
				ans += 1;
			}
		}
		
		System.out.println(ans);
		sc.close();
	}
}
