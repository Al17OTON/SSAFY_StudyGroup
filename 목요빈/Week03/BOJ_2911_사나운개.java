package 목요빈.Week03;

import java.util.Scanner;

public class BOJ_2911_사나운개 {
static int A, B, C, D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		A = sc.nextInt();
		B = sc.nextInt();
		C = sc.nextInt();
		D = sc.nextInt();
		
		int P = sc.nextInt();
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		sb.append(attack(P)+"\n");
		sb.append(attack(M)+"\n");
		sb.append(attack(N));
		System.out.println(sb);
		
		sc.close();
	}
	
	static int attack(int time) {
		int result = 0;
		if(time % (A+B) != 0 && time % (A+B) <= A) {
			result += 1;
		}
		
		if(time % (C+D) != 0 && time % (C+D) <= C) {
			result += 1;
		}
			
		return result;
	}
}
