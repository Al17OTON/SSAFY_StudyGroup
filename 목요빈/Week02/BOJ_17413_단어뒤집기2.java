package 목요빈.Week02;

import java.util.*;

public class BOJ_17413_단어뒤집기2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String str = sc.nextLine();
		
		LinkedList<Character> stk = new LinkedList<>();
		for(int i = 0; i<str.length(); i++) {
			char c = str.charAt(i);
			
			if(c == '<') {
				while(!stk.isEmpty()) {
					sb.append(stk.pollLast());
				}
				stk.add(c);
			}else if(c == '>') {
				while(!stk.isEmpty()) {
					sb.append(stk.pollFirst());
				}
				sb.append('>');
			}else if(c == ' ') {
				if(stk.contains('<')) {
					stk.add(' ');
					continue;
				}
				while(!stk.isEmpty()) {
					sb.append(stk.pollLast());
				}
				sb.append(' ');
			}else {
				stk.add(c);
			}
		}
		
		while(!stk.isEmpty())
			sb.append(stk.pollLast());
		
		System.out.println(sb);
		sc.close();
	}
}
