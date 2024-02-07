package 목요빈.Week03;

import java.util.Scanner;

public class BOJ_3985_롤케이크 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt();
		int N = sc.nextInt(); 
		
		int[] cake = new int[L+1];
		int expect = 0;
		int expect_idx = 0;
		int actual = 0; 
		int actual_idx = 0;
		
		for(int i = 1; i<=N; i++) {
			int p = sc.nextInt();
			int k = sc.nextInt();
			
			if(expect < k - p ) {
				expect = k - p;
				expect_idx = i;
			}
			
			for(int j = p; j<=k ; j++) {
				if(cake[j] == 0)
					cake[j] = i;
			}
		}
		
		for(int i = 1; i<=N; i++) {
			int count = 0;
			for(int j = 1; j<=L; j++) {
				if(cake[j] == i)
					count += 1;
			}
			if(count > actual) {
				actual = count;
				actual_idx = i;
			}
		}
		
		System.out.println(expect_idx);
		System.out.println(actual_idx);
		sc.close();
	}
}
