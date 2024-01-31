package 전영주.Week02;

import java.util.*;

public class BOJ_2798_블랙잭 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] arr = new int[n];
		 
		for (int i = 0; i < n; i++) {
			
			arr[i] = sc.nextInt();
			if(arr[i]>=m)i--;// 세 카드의 합이 무조건 m보다 작거나 같아야하므로 m이상 카드는 아예 안받기
		}
		System.out.println(search(arr, n,m));
	}
	static int search(int[] arr, int N, int M) {
		int answer = 0;
 
		//첫번째 카드
		for (int i = 0; i < N - 2; i++) {
	
			// 두번째 카드
			for (int j = i + 1; j < N - 1; j++) {
 
				// 두 카드의 합>m이면 다음 카드를 뽑을 필요 ㄴㄴ
				if(arr[i] + arr[j] > M) continue;
				
				// 세번째 카드
				for (int k = j + 1; k < N; k++) {
					
					int temp = arr[i] + arr[j] + arr[k];
					
					if (M == temp) return temp;//합이 m이면 다른 경우를 볼 필요 없음

					// answer 갱신 
					if(answer < temp && temp < M) {
						answer = temp;
					}
				}
			}
		}
		
		return answer;
	}
}
/*
n<100, m<3000_000이면  삼중 for문 가능. n^3
m도 삼백만이기에 int형으로 가능
++ 만약 카드 값이 m보다 크면 더할 필요 없음, 
++두번째 카드 까지 해서 m보다 크면 나머지 할 필요 없음

for문에 인덱싱에서 j=i+1부터 시작함-> 중복을 허용하지 않기 때문
만약 중복 허용이면 똑같이 0부터
만약 갯수가 for문으로 만들기 어렵다면?->for문이 아닌 재귀로 선택count매개변수를 받아야함
*/
