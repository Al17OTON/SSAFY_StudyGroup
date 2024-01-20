package SWEA.Num7272;

import java.util.Scanner;

public class Solution {
  
	static String onehole = "ADOPQR";   //구멍이 하나인 문자열
	
	static int countHole(char a) {
		if(a == 'B') return 2;
		
		for(int i = 0; i < onehole.length(); i++) {
			if(onehole.charAt(i) == a) return 0;
		}
		
		return 1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			String a = sc.next();
			String b = sc.next();
			boolean result = false;
			if(a.length() == b.length()) {
				result = true;
				for(int i = 0; i < a.length(); i++) {
					if(countHole(a.charAt(i)) != countHole(b.charAt(i))) {
						result = false;
						break;
					}
				}
			}
			
			System.out.println("#" + t + " " + (result ? "SAME" : "DIFF"));
			
		}
	}

}