package 목요빈.Week02;

import java.util.*;

// BOJ B2 1592 : 영식이와친구들
public class BOJ_1592_영식이와친구들 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		int ans = 0, idx = 0;
		
		int[] people = new int[N];
		people[0] = 1;
		
		while(true) {
			if(people[idx] == M)
				break;
			
			if(people[idx] % 2 == 1) { // 홀수이면
				idx = (idx + L) % N;
			}else { // 짝수이면
				idx = (idx + N + L) % N;
			}
			
			people[idx] += 1;
			ans += 1;
		}
		
		System.out.println(ans);
		sc.close();
	}
}
