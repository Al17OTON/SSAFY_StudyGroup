package 전영주.Week02;
import java.util.*;
import java.io.*;
public class BOJ_17413_단어뒤집기2 {

public static void main(String[] args) throws Exception{

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    char[] chars;
	    chars = reader.readLine().toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if(chars[i]=='<') {
				//태그 시작이므로 > 까지 i++
				while(chars[i++]!='>') {}
				//>까지 확인하고 ++하고 for 증감식으로 ++하기 때문에 하나 빼야함
				i--;
			}else if(chars[i]!=' ') {
				int start=i;
				//단어의 첫 인덱스와 끝 인덱스 구하
				while(i<chars.length&&chars[i]!='<'&&chars[i]!=' ') {
					i++;
				}
				int end=i-1;
				//단어 뒤집어 주기
				while(start<end) {
					char temp=chars[start];
					chars[start]=chars[end];
					chars[end]=temp;
					start++;
					end--;
				}
				//단어 다음인 현재 원소가 공백이면 i++
				//< 이면 for문에서 i++이므로 i--
				if(i<chars.length&&chars[i]=='<')i--;
			}
		}
        System.out.println(chars);
	}
}
/* S<100_000십만 n^2은 불가. 
 * 만약 태그면 바로 answer에 넣기
 * <가 나오면 answer에 넣기 >가 나올 때까지
 * >가 나왔거나 <가 안나왔으면 stack에 넣기
 * 공백이 나오면 그전에 나온 단어 뒤집어 넣어주기
 * 
 * 1. scanner와 String. charAt,deque : 309356KB	1616ms
 * 2. bufferedReader와 char[]
 * 3. deque를 쓰지 않고 인덱스로 접근해서 뒤와 앞을 바꾸기,tag일 경우 > 까지 한번에 더해주기
	:15760KB 160ms
 * 4. 3번 코드에서 scanner로 22532KB 348ms
 * 
 */
 
// // 첫코드
// public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		String in=sc.nextLine();
//		sc.close();
//		Deque<String> dq=new ArrayDeque<>();
//		String answer="";
//		boolean isTag=false;
//		for (int i = 0; i < in.length(); i++) {
//			if(isTag&&in.charAt(i)!='>') {
//				answer+=in.charAt(i);
//				continue;
//			}
//			if(in.charAt(i)=='<') {
//				while(!dq.isEmpty()) {
//					answer+=dq.pollLast();
//				}
//				answer+=in.charAt(i);
//				isTag=true;
//			}else if(in.charAt(i)=='>') {
//				answer+=in.charAt(i);
//				isTag=false;
//			}else if(in.charAt(i)==' ') {
//				while(!dq.isEmpty()) {
//					answer+=dq.pollLast();
//				}
//				answer+=in.charAt(i);
//			}else dq.add(Character.toString(in.charAt(i)));
//		}
//		while(!dq.isEmpty()) {
//			answer+=dq.pollLast();
//		}
//		System.out.println(answer);
//	}