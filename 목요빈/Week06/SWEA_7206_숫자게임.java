package 목요빈.Week06;

import java.io.*;
import java.util.*;

public class SWEA_7206_숫자게임 {
	
	static int max;
	static int[] numbers, dp;
	// 자르는 자리 위한 조합 -> N-1 C 2
	
	/*
	 1. 수 쪼개기
	 2. 쪼갠 수 곱하기
	 3. 곱한 수가 10 이상이면 더 이상 쪼갤 수 없을 때가지 위 과정 반복
	 
	 한 번 쪼개질 때마다 turn 수 증가 -> 최대 턴수가 나오는 경우?
	 
	 * */
	
	// 29/50
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			String N = br.readLine();
			
			int n = Integer.parseInt(N);
			dp = new int[n];
			numbers = new int[N.length()-1];

			if(N.length() >= 100) 
				comb(0, 0, n);
			split1(n, 0);
			System.out.println("#" + test_case + " " + max);
		}
	}
	
	// 수를 한 번 자르는 경우
	static void split1(int n, int cnt) {
		String str = Integer.toString(n);
		if(str.length()==1) {
			max = Math.max(cnt, max);
			return;
		}
		
		for (int i = 1; i < str.length(); i++) {
			int a = Integer.parseInt(str.substring(0, i));
			int b = Integer.parseInt(str.substring(i));
//			System.out.println(a + " * " + b + " = " + a * b);
			split1(a * b, cnt+1);
		}
	}
	
	// 수를 두 번 자르는 경우
	static void split2(int n, int cnt, int[] split) {
		String str = Integer.toString(n);
		
		Arrays.sort(split);
		int a = Integer.parseInt(str.substring(0, split[0]));
		int b = Integer.parseInt(str.substring(split[0], split[1]));
		int c = Integer.parseInt(str.substring(split[1]));
		
		int result = a*b*c;
		if(Integer.toString(result).length() < 100) {
			split1(result, cnt+1);
		}else {
			comb(0, 0, result);
		}
	}
	
	static void comb(int n, int idx, int num) {
		if(n == 2) {
			System.out.println(Arrays.toString(numbers));
			split2(num, 0, numbers);
			return;
		}
		
		int size = Integer.toString(num).length()-1;
		for (int i = idx; i < size; i++) {
			numbers[n] = i+1;
			comb(n+1, i+1, num);
		}
	}
}
