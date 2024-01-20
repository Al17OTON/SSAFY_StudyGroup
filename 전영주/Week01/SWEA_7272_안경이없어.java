import java.util.*;

class Solution
{	
	static String[] data= { "C","E","F","G","H","I","J","K","L","M","N","S","T","U","V","W","X","Y","Z"};
	static List<String> noRing=new ArrayList<>(Arrays.asList(data)); //
	
	static String[] data2= {"A","D","O","P","Q","R"};
	static List<String> oneRing=new ArrayList<>(Arrays.asList(data2));
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String a=sc.next();
			String b=sc.next();
			System.out.println("#"+test_case+" "+checkSame(a, b));
		}
	}
	public static String checkSame(String a,String b) {
		if(a.length()!=b.length())return "DIFF"; //길이가 같지 않다면 f
		for (int i = 0; i < a.length(); i++) {
			String ai=Character.toString(a.charAt(i));
			String bi=Character.toString(b.charAt(i));
			if(ai.equals(bi)) continue; //문자가 동일하다면 넘어감
			if(oneRing.contains(ai)&&oneRing.contains(bi))continue; //구명이 한개짜리라면 넘어감
			if(noRing.contains(ai)&&noRing.contains(bi))continue; //구명이 두개짜리라면 넘어감
			return "DIFF";
		}
		return "SAME";
	}
}
/*
 문자열 길이 <=10
 CEFGHIJKLMNSTUVWXYZ 는 같은 문자  0
 ADOPQR 는 같은 문자 1
 B 정확히 하나
  
 1. B의 위치, 개수에 따라 1차 판별, 구멍 두개짜리 2차 판별,,,
 2. for문 돌려서 그냥 첨부터 확인 : O(n*k) n은 문자열 길이, k는 문자 개수
 
 String은 꼭 .equals로 비교!
 */

/*
 다른 사람 코드: 정규식을 통해 replaceAll 한 후 두 문자열이 같은 지 확인.
 => 내코드보다 100ms정도 빠름
text = text.replaceAll("C|E|F|G|H|I|J|K|L|M|N|S|T|U|V|W|X|Y|Z", "-");
text = text.replaceAll("A|D|O|P|Q|R", "0");
text = text.replace('B', '8');
 */
