package Algoritm_homework;

import java.io.*;
import java.util.*;

public class 재관이의대량할인 {
	public static void main(String[]args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int T=Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n=Integer.parseInt(br.readLine());
			List <Integer> arr=new ArrayList<>();
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			//정렬
			Collections.sort(arr);
			int answer=0;
			int index=arr.size()-1;//몇번째 상품을 더해주는지

			while(index>=2) {//만약 3으로 나눠지면 끝까지 계산된다
				int count=0;
				while(count<2) {
					answer+=arr.get(index);
					count++;
					index--;
				}
				index--;
			}
      //남은 애들 더해죽(3으로 나눠지지 않는 경우)
			while(index>=0) {
				answer+=arr.get(index);
				index--;
			}
			
			System.out.println("#"+t+" "+answer);
		}
		
	}
}
/*
 *세벌의 옷 중 가장 싼 애 ㄴㄴ
 *가장 많이 할인 받을 수 있는 거
 *부르트 포스? 걍 정렬해서 3으로 나누면되는거 아닌가
 *그리고 뒤에서부터 3개씩 자르기.
 */
