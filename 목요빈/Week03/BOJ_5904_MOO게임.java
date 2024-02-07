package 목요빈.Week03;

import java.util.Scanner;

public class BOJ_5904_MOO게임 {
	
	static int N, idx, mid, str;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // Moo 수열의 N번째 글자
		
		str = idx = 0;
		mid = 2;
		
		moo(0);
		sc.close();
	}
	
	// N번째 문자가 속한 S(k) 구하기
	private static void moo(int k) {
		if(str >= N) {
			calc(str, mid, k-1, N);
			return;
		}

		mid += 1;
		str = str + mid + str;
		moo(k+1);
	}

	// S(k)의 길이, 중간값 길이, k, 찾는 문자열 인덱스인 n을 매개변수로 받음
	private static void calc(int str, int mid, int k, int n) {
		// str, mid를 통해 39번 줄에 쓰이는 조건값들을 구할 수 있음
		int side = (str - mid) / 2;
		
		// m이 위치한 자리는 각 단어의 첫 자리 = 시작, 중간, 마지막
		if(n == 1 || n == side + 1 || n == side + mid + 1) {
			System.out.println("m");
			return;
		}
		
		// 문자열이 "moo"에 도달했는데도 return 못함 = "m"이 아님
		if(k == 0) {
			System.out.println("o");
			return;
		}
	
		// 중간 이후, 이전값이냐에 따라 찾는 문자열 인덱스 바꿔주기
		if(n > side)
			calc(side, mid-1, k-1, n-side-mid);
		else
			calc(side, mid-1, k-1, n);
	}
}
