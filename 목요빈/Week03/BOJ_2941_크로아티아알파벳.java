package 목요빈.Week03;

import java.io.*;

public class BOJ_2941_크로아티아알파벳 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();
		int len = str.length();
		for (int i = 1; i < str.length(); i++) {
			if(str.charAt(i) == '=') {
				if(str.charAt(i-1) == 'c' || str.charAt(i-1) == 's') {
					len -= 1;
				}else if(str.charAt(i-1) == 'z') {
					if(i >= 2 && str.charAt(i-2) == 'd') len -= 1;
					len -= 1;
				}
			}else if(str.charAt(i) == '-') {
				if(str.charAt(i-1) == 'c' || str.charAt(i-1) == 'd') {
					len -= 1;
				}
			}else if(str.charAt(i) == 'j') {
				if(str.charAt(i-1) == 'l' || str.charAt(i-1) == 'n') {
					len -= 1;
				}
			}
		}
		
		System.out.println(len);
	} 
}
