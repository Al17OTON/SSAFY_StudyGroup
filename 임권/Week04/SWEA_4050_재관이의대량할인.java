package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_4050_재관이의대량할인 {
	static int[] arr;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int result = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for(int n = 0; n < N; n++) {
				arr[n] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);							//그리디로 풀기위해 정렬
			
			int i;
			for(i = arr.length - 1; i >= arr.length % 3; i--) {	//오름차순이므로 뒤에서 부터 계산, 3으로 나누어 떨어지는 경우만 합산
				result += arr[i];
				i--;
				result += arr[i];
				i--;
			}
			for(; i >= 0; i--) {						//만약 남은 물건이 있다면 구매하기
				result += arr[i];
			}
			
			System.out.println("#" + t + " " + result);
		}
	}

}
