package Algoritm_homework;
import java.util.*;
import java.io.*;
public class 보물상자비밀번호 {
	public static void main(String[]args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		StringTokenizer st=new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			HashSet <Integer> createdNum=new HashSet<>();
			st=new StringTokenizer(br.readLine());
			sb=new StringBuilder();
			sb.append(st.nextToken());
			int count=n/4;//총 돌아야하는 횟수 n/4 이후는 반복된다
			while(count>0) {
				for (int i = 0; i < n; i+=n/4) {
					String temp="";
					for (int j = i; j < n/4+i; j++) {
						temp+=Character.toString(sb.charAt(j));
					}
					createdNum.add(Integer.parseInt(temp.toString(),16));				
				}
        //스트링빌더로 스트링 자체를 바꿔주기
				char a=sb.charAt(n-1);
				sb.deleteCharAt(n-1);
				sb.insert(0, a);
				count--;
			}
			int [] arr=new int [createdNum.size()];
			count=0;
			for(Integer i:createdNum) {
				arr[count]=(int)i;
				count++;
			}
			Arrays.sort(arr);
			System.out.println("#"+t+" "+arr[count-k]);
			
		}		
	}
}
/* 
 * n<28 4의 배수, 0~F  3초
 * 보물 상자에 적힌 숫자로 만들 수 있는 숫자중 k번째 s
 * 회전 하면서 생성 가능한 수를 나열 한 후 거기서
 *  k번째
 * 중복이 있을 수 있으므로 회전 하면서 뭐가 생성된지 개수 확인-> set으로
 * n/4개씩 나누면서
 * 오래걸린 이유:
 * temp=""로 시작하니 분명 numberFormatException이 났는데 이제 다시 안남;; 뭐지
 * set에서 마지막 원소를 가져가고 싶었는데 안되냐 왜
 * 생성 가능한 수를 내림!! 차순
 * 
 * ## 강사님께서 이런 문제는 무조건 set이 아니라 다른 방법으로 중복 제거를 하는 방법을 사용하는게 시간적으로도 이득이고 귀찮지 않다.
 * ++ 스트링빌더는 테케 마다 초기화해줘야한다!!
 */
