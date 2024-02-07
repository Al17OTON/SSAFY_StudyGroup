package 목요빈.Week04;

import java.util.*;

public class SWEA_5658_보물상자비밀번호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++){
			int N = sc.nextInt();
			int K = sc.nextInt();
			String str = sc.next();
			Set<String> set = new HashSet<>();
			
			int len = N / 4; // 한 변의 숫자 길이 + len번 돌면 반복
			
			for(int i = 0; i < len; i++) {
				String start = str.substring(0, 1);
				for(int j = 0; j < N; j += len) {
					String temp = str.substring(j, j + len);
					set.add(temp);
				}
				str = str.substring(1) + start;
			}
			
			// set -> list -> 정렬 후 몇 번째인지
			Integer[] arr = new Integer[set.size()];
			int idx = 0;
			for (String hex : set) {
				arr[idx] = Integer.parseInt(hex, 16);
				idx += 1;
			}
			
			Arrays.sort(arr, Comparator.reverseOrder());
			
			System.out.printf("#%d %d\n", test_case, arr[K-1]);
		}
		sc.close();
	}
}
