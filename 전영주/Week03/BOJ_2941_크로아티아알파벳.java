package 전영주.Week03;

import java.util.Scanner;

public class BOJ_2941_크로아티아알파벳 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		StringBuilder str=new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			str.append(s.charAt(i));
		}
		int answer=0;
		//알파벳과 - = 만 나오기 때문에 숫자로 바꿔주기

		replaceAll(str,"c=", "0");
		replaceAll(str,"c-", "0");
		replaceAll(str,"dz=", "0");
		replaceAll(str,"d-", "0");
		replaceAll(str,"lj", "0");
		replaceAll(str,"nj", "0");
		replaceAll(str,"s=", "0");
		replaceAll(str,"z=", "0");
		
		answer=str.length();
		System.out.println(answer);
//		replaceAll(str,"0","");
//		answer-=str.length();//원래 길이- 바뀐 길이= 0 개수=> 크로아티아 알파벳 갯수
//		System.out.println(answer);
	}
	public static void replaceAll(StringBuilder builder, String from, String to) {
	    int index = builder.indexOf(from);
	    while (index != -1) {
	        builder.replace(index, index + from.length(), to);
	        index += to.length(); // Move to the end of the replacement
	        index = builder.indexOf(from, index);
	    }	       
	}
}
/*
 * 맨첨엔 알파벳은 크로아티아 가 아닌 줄알았지만 표에 없어도 그냥 알파벳도 크로아티아 알파벳임
 * 
 */