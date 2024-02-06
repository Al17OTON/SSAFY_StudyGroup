package Week04;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SWEA_5658_보물상자비밀번호 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> pq;
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			pq = new PriorityQueue<>(Collections.reverseOrder());
			int N = sc.nextInt();
			int K = sc.nextInt();
			sc.nextLine();
			String nums = sc.nextLine();
			
			int len = nums.length() / 4;
			
			for(int z = 0; z < len; z++) {
				for(int i = 0; i < 4; i++) {
					//pq.add(hex2dec(nums.substring(i * nums.length() / 4, nums.length() / 4)));
					String a = "";
					for(int j = i * len + z; j < i * len + len + z; j++) {
						a += nums.charAt(j % nums.length());
					}
					pq.add(hex2dec(a));
				}
			}
			int pre = 0, tmp;
			while(K > 0) {
				tmp = pq.poll();
				if(pre != tmp) K--;
				pre = tmp;
			}
			
			System.out.println("#" + t + " " + pre);
		}
	}

	static int hex2dec(String a) {
		int result = 0, h = 1;
		for(int i = a.length() - 1; i >= 0; i--) {
			if(a.charAt(i) >= '0' && a.charAt(i) <= '9') {
				result += (a.charAt(i) - '0') * h;
			} else {
				result += ((a.charAt(i) - 'A') + 10) * h;
			}
			h *= 16;
		}
		return result;
	}
}
