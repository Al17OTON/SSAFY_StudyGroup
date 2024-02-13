package Week05;

import java.util.Scanner;

public class SWEA_4366_정식이의은행업무 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int t = 1; t <= T; t++) {
			int bin = 0;
			char[] a = sc.nextLine().toCharArray();
			char[] b = sc.nextLine().toCharArray();
			
			L : for(int i = 0; i < a.length; i++) {			//모든 자릿수 반전
				
				if(a[i] == '0') a[i] = '1';
				else a[i] = '0';
					
				for(int j = 0; j < b.length; j++) {			//모든 자릿수 0~2 넣기 (같은 경우 제외)
					char tmp = b[j];
					bin = convert2Dec(a, 2);
					
					if(tmp != '0') {
						b[j] = '0';
						
						if(bin == convert2Dec(b, 3)) {
							break L;
						}
					}
					
					if(tmp != '1') {
						b[j] = '1';
						
						if(bin == convert2Dec(b, 3)) {
							break L;
						}
					}
					
					if(tmp != '2') {
						b[j] = '2';
						
						if(bin == convert2Dec(b, 3)) {
							break L;
						}
					}
					
					
					b[j] = tmp;		//원상복구
				}
				
				if(a[i] == '0') a[i] = '1';	//원상복구
				else a[i] = '0';
				 
			}
			
			System.out.println("#" + t + " " + bin);
			
		}
	}
	
	
	static int convert2Dec(char[] a, int n) {	//n 값은 n진수
		int result = 0, pow = 1;
		
		for(int i = a.length - 1; i >= 0; i--) {
			result += (a[i] - '0') * pow;
			pow *= n;
		}
		return result;
	}

}
