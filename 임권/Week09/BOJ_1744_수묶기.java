package Week09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_1744_수묶기 {
	
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pos = new PriorityQueue<>(Collections.reverseOrder()), neg = new PriorityQueue<>();
		
		
		for(int n = 0; n < N; n++) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp > 0) pos.add(tmp);
			else neg.add(tmp);
		}
		
		
		int sum = 0;
		while(pos.size() > 1) {
			int a = pos.poll();
			int b = pos.peek();
			if(a * b > a + b) {
				b = pos.poll();
				sum += a * b;
			} else sum += a;
		}
		if(!pos.isEmpty()) sum += pos.poll();
		
		while(neg.size() > 1) {
			int a = neg.poll();
			int b = neg.poll();
			sum += a * b;
		}
		if(!neg.isEmpty()) sum += neg.poll();
		
		
		System.out.println(sum);
		
	}
}







